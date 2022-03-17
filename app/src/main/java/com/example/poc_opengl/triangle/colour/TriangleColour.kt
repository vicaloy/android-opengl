package com.example.poc_opengl.triangle.colour
import android.opengl.GLES20
import com.example.poc_opengl.loadShader
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer


class TriangleColour {
    private val vertexShaderCode = ("attribute vec3 aVertexPosition;"
            + "uniform mat4 uMVPMatrix;" +
            "varying vec4 vColor;" +
            "attribute vec4 aVertexColor;" +  //color of each vertex
            "void main() {" +
            "gl_Position = uMVPMatrix *vec4(aVertexPosition,1.0);" +
            "vColor=aVertexColor;" +
            "}")
    private val fragmentShaderCode = "precision mediump float;varying vec4 vColor; " +
            "void main() {gl_FragColor = vColor;}"
    private val vertexBuffer: FloatBuffer
    private val colorBuffer: FloatBuffer
    private val mProgram: Int
    private val mPositionHandle: Int
    private val mColorHandle: Int
    private val mMVPMatrixHandle: Int
    private val vertexCount // number of vertices
            : Int
    private val vertexStride = COORDS_PER_VERTEX * 4 // 4 bytes per vertex
    private val colorStride = COLOR_PER_VERTEXT * 4
    fun draw(mvpMatrix: FloatArray?) {
        // Apply the projection and view transformation
        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mvpMatrix, 0)
        //set the attribute of the vertex to point to the vertex buffer
        GLES20.glVertexAttribPointer(
            mPositionHandle, COORDS_PER_VERTEX,
            GLES20.GL_FLOAT, false, vertexStride, vertexBuffer
        )
        //set the attribute of the color to point to the color buffer
        GLES20.glVertexAttribPointer(
            mColorHandle,
            COLOR_PER_VERTEXT,
            GLES20.GL_FLOAT,
            false,
            colorStride,
            colorBuffer
        )
        // Draw the triangle
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount)
    }

    companion object {
        // number of coordinates per vertex in this array
        const val COORDS_PER_VERTEX = 3
        const val COLOR_PER_VERTEXT = 4
        var triangleVertex = floatArrayOf(
            -1.0f,-1.0f,1.0f,
            1.0f,-1.0f,1.0f,
            0.0f,1.0f,1.0f
        )
        var triangleColor = floatArrayOf(
            1f, 0f, 0f, 1f,
            0f, 1f, 0f, 1f,
            0f, 0f, 1f, 1f,
        )
    }

    init {
        val bb = ByteBuffer.allocateDirect(triangleVertex.size * 4)
        bb.order(ByteOrder.nativeOrder())
        vertexBuffer = bb.asFloatBuffer()
        vertexBuffer.put(triangleVertex)
        vertexBuffer.position(0)
        vertexCount = triangleVertex.size / COORDS_PER_VERTEX
        val cb = ByteBuffer.allocateDirect(triangleColor.size * 4)
        cb.order(ByteOrder.nativeOrder())
        colorBuffer = cb.asFloatBuffer()
        colorBuffer.put(triangleColor)
        colorBuffer.position(0)
        val vertexShader: Int = loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode)
        val fragmentShader: Int = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode)
        mProgram = GLES20.glCreateProgram() // create empty OpenGL Program
        GLES20.glAttachShader(mProgram, vertexShader) // add the vertex shader to program
        GLES20.glAttachShader(mProgram, fragmentShader) // add the fragment shader to program
        GLES20.glLinkProgram(mProgram) // link the  OpenGL program to create an executable
        GLES20.glUseProgram(mProgram) // Add program to OpenGL environment
        // get handle to vertex shader's vPosition member
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "aVertexPosition")
        // get handle to the vertex shader's aVertexColor
        mColorHandle = GLES20.glGetAttribLocation(mProgram, "aVertexColor")
        // Enable a handle to the triangle vertices
        GLES20.glEnableVertexAttribArray(mPositionHandle)
        // Enable the handle for the color
        GLES20.glEnableVertexAttribArray(mColorHandle)
        // get handle to shape's transformation matrix
        mMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix")
    }
}