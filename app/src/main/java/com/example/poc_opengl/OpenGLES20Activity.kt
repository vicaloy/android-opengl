package com.example.poc_opengl

import android.app.Activity
import android.opengl.GLSurfaceView
import android.os.Bundle
import com.example.poc_opengl.square.SquareGLSurfaceView
import com.example.poc_opengl.triangle.colour.TriangleColourGLSurfaceView
import com.example.poc_opengl.triangle.rotate.TriangleRotateGLSurfaceView
import com.example.poc_opengl.triangle.simple.TriangleSimpleGLSurfaceView

class OpenGLES20Activity : Activity() {

    private lateinit var gLView: GLSurfaceView

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val choose = intent.extras?.get("shape")
        if (choose != null) {
            // Create a GLSurfaceView instance and set it
            // as the ContentView for this Activity.
            when (choose as Int) {
                0 -> gLView = SquareGLSurfaceView(this)
                1 -> gLView = TriangleSimpleGLSurfaceView(this)
                2 -> gLView = TriangleRotateGLSurfaceView(this)
                3 -> gLView = TriangleColourGLSurfaceView(this)

            }
        }

        setContentView(gLView)
    }
}