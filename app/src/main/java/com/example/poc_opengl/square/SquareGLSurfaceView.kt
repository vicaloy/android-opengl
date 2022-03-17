package com.example.poc_opengl.square

import android.content.Context
import android.opengl.GLSurfaceView

class SquareGLSurfaceView(context: Context) : GLSurfaceView(context) {

    private val renderer: SquareGLRenderer

    init {

        // Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(2)

        renderer = SquareGLRenderer()

        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(renderer)
        // Render the view only when there is a change in the drawing data
        renderMode = GLSurfaceView.RENDERMODE_WHEN_DIRTY
    }
}
