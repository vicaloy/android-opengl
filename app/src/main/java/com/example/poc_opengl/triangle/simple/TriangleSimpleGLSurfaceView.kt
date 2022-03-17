package com.example.poc_opengl.triangle.simple

import android.content.Context
import android.opengl.GLSurfaceView

class TriangleSimpleGLSurfaceView(context: Context) : GLSurfaceView(context) {

    private val rendererSimple: TriangleSimpleGLRenderer

    init {

        // Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(2)

        rendererSimple = TriangleSimpleGLRenderer()

        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(rendererSimple)
        // Render the view only when there is a change in the drawing data
        renderMode = GLSurfaceView.RENDERMODE_WHEN_DIRTY
    }
}
