package com.example.location.activities.user;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.CamcorderProfile;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;


import com.example.location.R;
import com.example.location.activities.user.fragments.ItemListDialogFragment;
import com.example.location.dummy.ImagenesLocales;
import com.example.location.helpers.ARManager;
import com.example.location.helpers.AnimationManager;
import com.example.location.helpers.TomarFoto;
import com.example.location.helpers.GrabarVideo;
import com.example.location.model.Imagen;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.ux.ArFragment;

import java.io.File;
import java.util.*;

import io.github.douglasjunior.androidSimpleTooltip.SimpleTooltip;

public class ImagenActivity extends AppCompatActivity {
    //DatabaseReference imageRef = FirebaseDatabase.getInstance().getReference("imagens");
    private static final String TAG = "TAG";
    private static final double MIN_OPENGL_VERSION = 3.0;
    private static final int CAMERA_PERMISSION_CODE = 100;
    public ArFragment arFragment;
    ARManager arManager;
    BottomAppBar bottomAppBar;
    FloatingActionButton fabCamera, fabVideo, fabSearch, fabExitFull, fabClose, fabInfo, fabRemove;
    private boolean isFullScreenMode = false;
    private AnimationManager _animationManager;
    TomarFoto tomarFoto;
    private Context _context;
    String imageAnchor = "dinosaur/allosaurus.sfb";
    CoordinatorLayout main_content;
    GrabarVideo grabarVideo;
    Imagen imagen;
    ArrayList<Imagen> imagens = new ArrayList<>();

    Imagen choosingImagen;
    AnchorNode choosingAnchorNode;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_image);
        imagen = (Imagen) getIntent().getSerializableExtra("data");
        setImageAnchor(imagen);
        getImageList();
        setId();
        _animationManager = new AnimationManager(this);
        if (!checkIsSupportedDeviceOrFinish(this)) {
            return;
        }
        setUpBottomAppBar();
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ux_fragment);
        // This part of the code will be executed when the user taps on a plane
        arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
            Anchor anchor = hitResult.createAnchor();
            arManager = new ARManager(arFragment, this);
            if (imageAnchor.indexOf(".sfb") == -1) {
                arManager.create3DModel(anchor, imageAnchor);
            } else {
                arManager.create3DModelSFB(anchor, imageAnchor);
            }

        });
        _context = this;

        setListFragmentImage();
        takePhoto();
        Recording();
    }

    private void getImageList() {
        ImagenesLocales img=new ImagenesLocales(1);
        List<Imagen> allimg=new ArrayList<>();
        allimg=img.List();
        for (Imagen c:allimg) {
            imagens.add(c);
            Log.d("->>>>>>>>>>", "Value is: " + c.getName());
        }
        /*ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                imagens = new ArrayList<>();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Imagen img = child.getValue(Imagen.class);
                    if (img != null && img.getGroup().equals(imagen.getGroup())) {
                        imagens.add(img);
                    }
                }
                Log.d("TAG", "Value is: " + dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        };
        imageRef.addValueEventListener(eventListener);*/
    }

    public ArrayList<Imagen> getImagens() {
        return imagens;
    }

    private void setImageAnchor(Imagen imagen) {
        Log.d(TAG, "setImageAnchor: "+ imagen);
        if (imagen.getUrl() != null && !imagen.getUrl().isEmpty()) {
            imageAnchor = imagen.getUrl();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void Recording() {
        grabarVideo = new GrabarVideo();
        int orientation = getResources().getConfiguration().orientation;
        grabarVideo.setVideoQuality(CamcorderProfile.QUALITY_2160P, orientation);
        grabarVideo.setSceneView(arFragment.getArSceneView());
        fabVideo.setOnClickListener(this::toggleRecording);
        fabVideo.setEnabled(true);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void toggleRecording(View unusedView) {
        boolean recording = grabarVideo.onToggleRecord();
        if (recording) {
            fabVideo.setImageResource(R.drawable.videocam_off);
        } else {
            fabVideo.setImageResource(R.drawable.videocam);
            String videoPath = grabarVideo.getVideoPath().getAbsolutePath();

            // Send  notification of updated content.
            ContentValues values = new ContentValues();
            values.put(MediaStore.Video.Media.TITLE, "Sceneform Video");
            values.put(MediaStore.Video.Media.MIME_TYPE, "video/mp4");
            values.put(MediaStore.Video.Media.DATA, videoPath);
            getContentResolver().insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, values);
            File photoFile = new File(videoPath);
            Uri photoURI = FileProvider.getUriForFile(this,
                    this.getPackageName() + ".ar.codelab.name.provider",
                    photoFile);
            Intent intent = new Intent(Intent.ACTION_VIEW, photoURI);
            intent.setDataAndType(photoURI, "video/*");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(intent);
        }
    }

    private void takePhoto() {
        fabCamera.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                tomarFoto.takePhoto(arFragment.getArSceneView(), _context, main_content);
            }
        });
    }

    private void setListFragmentImage() {
        fabSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialogFragment bottomSheetDialogFragment = ItemListDialogFragment.newInstance();
                bottomSheetDialogFragment.show(getSupportFragmentManager(), "list imagen");
            }
        });
    }

    private void setId() {
        main_content = findViewById(R.id.main_content);
        fabCamera = findViewById(R.id.btnCamera);
        fabVideo = findViewById(R.id.btnVideo);
        fabSearch = findViewById(R.id.btnSearch);
        fabExitFull = findViewById(R.id.BtnExitFull);
        fabClose = findViewById(R.id.btnClose);
        fabInfo = findViewById(R.id.btnInfo);
        fabRemove = findViewById(R.id.btnRemove);
        tomarFoto = new TomarFoto();
        fabExitFull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fabExitFull.setVisibility(View.INVISIBLE);
                _animationManager.slideUp(bottomAppBar);
                Animation showAnim = AnimationUtils.loadAnimation(ImagenActivity.this, R.anim.scale_up);
                fabCamera.show();
                fabSearch.show();
                fabVideo.show();
                fabCamera.startAnimation(showAnim);
                fabSearch.startAnimation(showAnim);
                fabVideo.startAnimation(showAnim);
                isFullScreenMode = false;
            }
        });
        fabClose.setVisibility(View.INVISIBLE);
        fabRemove.setVisibility(View.INVISIBLE);
        fabInfo.setVisibility(View.INVISIBLE);
        fabClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fabClose.setVisibility(View.INVISIBLE);
                fabRemove.setVisibility(View.INVISIBLE);
                fabInfo.setVisibility(View.INVISIBLE);
            }
        });
        fabInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInfo();
            }
        });
        fabRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeAnchorNode();
            }
        });
    }

    private void setUpBottomAppBar() {
        bottomAppBar = findViewById(R.id.bottomAppBar);
        setSupportActionBar(bottomAppBar);
        bottomAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.btnFull:
                        handleBtnFull();
                        break;
                }
                return false;
            }
        });
        bottomAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void initFullScreenMode(View view) {
        _animationManager.setBottomBarHeight(bottomAppBar.getHeight());

        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = 0;
        view.setLayoutParams(params);

//        spinner.setVisibility(View.GONE);
        showExitFullScreenFab();
    }

    private void showExitFullScreenFab() {
        Animation showAnim = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        fabExitFull.setVisibility(View.VISIBLE);
        fabExitFull.startAnimation(showAnim);
    }

    private void handleBtnFull() {
        Animation hideAnim = AnimationUtils.loadAnimation(this, R.anim.scale_down);
        hideAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                fabCamera.hide();
                fabSearch.hide();
                fabVideo.hide();
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        fabCamera.startAnimation(hideAnim);
        fabSearch.startAnimation(hideAnim);
        fabVideo.startAnimation(hideAnim);
        _animationManager.slideDown(bottomAppBar);
        isFullScreenMode = true;
    }

    public static boolean checkIsSupportedDeviceOrFinish(final Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            Log.e(TAG, "Sceneform requires Android N or later");
            Toast.makeText(activity, "Sceneform requires Android N or later", Toast.LENGTH_LONG).show();
            activity.finish();
            return false;
        }
        String openGlVersionString =
                ((ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE))
                        .getDeviceConfigurationInfo()
                        .getGlEsVersion();
        if (Double.parseDouble(openGlVersionString) < MIN_OPENGL_VERSION) {
            Log.e(TAG, "Sceneform requires OpenGL ES 3.0 later");
            Toast.makeText(activity, "Sceneform requires OpenGL ES 3.0 or later", Toast.LENGTH_LONG)
                    .show();
//            activity.finish();
            return false;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.close, menu);
        MenuItem menuItem = menu.findItem(R.id.btnFull);
        return super.onCreateOptionsMenu(menu);
    }


    public void setImagen(Imagen data) {
        imageAnchor = data.getUrl();
    }

    private static final String REQUIRED_PERMISSIONS[] = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };

    /**
     * Check to see we have the necessary permissions for this app.
     */
    public static boolean hasCameraPermission(Activity activity) {
        for (String p : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(activity, p) !=
                    PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check to see we have the necessary permissions for this app,
     * and ask for them if we don't.
     */
    public static void requestCameraPermission(Activity activity) {
        ActivityCompat.requestPermissions(activity, REQUIRED_PERMISSIONS,
                CAMERA_PERMISSION_CODE);
    }

    /**
     * Check to see if we need to show the rationale for this permission.
     */
    public static boolean shouldShowRequestPermissionRationale(Activity activity) {
        for (String p : REQUIRED_PERMISSIONS) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, p)) {
                return true;
            }
        }
        return false;
    }

    public void showOptionsMenu(AnchorNode anchorNode, String url) {
        choosingAnchorNode = anchorNode;
        choosingImagen = getImageByUrl(url);
        fabClose.setVisibility(View.VISIBLE);
        fabInfo.setVisibility(View.VISIBLE);
        fabRemove.setVisibility(View.VISIBLE);
    }

    private Imagen getImageByUrl(String url) {
        for (int i = 0; i < imagens.size(); i++) {
            if (imagens.get(i).getUrl().equals(url)) {
                return imagens.get(i);
            }
        }
        return null;
    }

    private void showInfo() {
        if (choosingImagen != null) {
            new SimpleTooltip.Builder(this)
                    .anchorView(fabInfo)
                    .text((choosingImagen.getLongDesc() != "" && choosingImagen.getLongDesc() != null) ? choosingImagen.getLongDesc() : choosingImagen.getDesc())
                    .gravity(Gravity.TOP)
                    .animated(true)
                    .build()
                    .show();
//            AlertDialog.Builder builder = new AlertDialog.Builder(_context);
//            builder.setMessage(choosingImagen.getDesc()).show();
        }
    }

    private void removeAnchorNode() {
        if (choosingAnchorNode != null) {
            arFragment.getArSceneView().getScene().removeChild(choosingAnchorNode);
            choosingAnchorNode.getAnchor().detach();
            choosingAnchorNode.setParent(null);
        }
    }
}