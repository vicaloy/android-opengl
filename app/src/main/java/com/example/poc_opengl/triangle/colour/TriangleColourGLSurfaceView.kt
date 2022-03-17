package com.example.poc_opengl.triangle.colour

import android.content.Context
import android.opengl.GLSurfaceView

class TriangleColourGLSurfaceView(context: Context) : GLSurfaceView(context) {

    private val rendererSimple: TriangleColourGLRenderer

    init {

        // Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(2)

        rendererSimple = TriangleColourGLRenderer()

        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(rendererSimple)
        // Render the view only when there is a change in the drawing data
        renderMode = GLSurfaceView.RENDERMODE_WHEN_DIRTY
    }
}
