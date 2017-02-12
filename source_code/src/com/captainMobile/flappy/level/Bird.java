package com.captainMobile.flappy.level;

import com.captainMobile.flappy.graphics.Shader;
import com.captainMobile.flappy.graphics.Texture;
import com.captainMobile.flappy.graphics.VertexArray;
import com.captainMobile.flappy.input.Input;
import com.captainMobile.flappy.maths.Matrix4f;
import com.captainMobile.flappy.maths.Vector3f;
import org.lwjgl.glfw.GLFW;

/**
 *
 * @author joaor
 */
public class Bird {
    private float SIZE = 1.0f;
    private VertexArray mesh;
    private Texture texture;
    private Vector3f position = new Vector3f();
    private float rot;
    private float delta = 0.0f;
    
    public Bird() {
        float[] vertices = new float[]{
            -SIZE / 2.0f, -SIZE / 2.0f, 0.1f,
            -SIZE / 2.0f, SIZE / 2.0f, 0.1f,
            SIZE / 2.0f, SIZE / 2.0f, 0.1f,
            SIZE / 2.0f, -SIZE / 2.0f, 0.1f
        };
        
        byte[] indices = new byte[]{
          0, 1, 2,
          2, 3, 0
        };
        
        float[] tcs = new float[]{
            0, 1,
            0, 0,
            1, 0,
            1, 1
        };
        
        mesh = new VertexArray(vertices, indices, tcs);
        texture = new Texture("res/bird.png");
    }
    
    public void update() {
        /*if(Input.keys[GLFW.GLFW_KEY_UP]) position.y += 0.1f;
        if(Input.keys[GLFW.GLFW_KEY_DOWN]) position.y -= 0.1f;
        if(Input.keys[GLFW.GLFW_KEY_LEFT]) position.x -= 0.1f;
        if(Input.keys[GLFW.GLFW_KEY_RIGHT]) position.x += 0.1f;*/
        position.y -= delta;
        if(Input.isKeyDown(GLFW.GLFW_KEY_SPACE)) delta = -0.15f;
        else delta += 0.01f;
        
        rot = -delta * 90.0f;
    }
    
    public void fall() {
        delta = -0.15f;
    }
    
    public void render() {
        Shader.BIRD.enable();
        Shader.BIRD.setUniformMat4f("ml_matrix", Matrix4f.translate(position).multiply(Matrix4f.rotate(rot)));
        texture.bind();
        mesh.render();
        Shader.BIRD.disable();
    }

    public float getY() {
        return position.y;
    }
    
    public float getSize() {
        return SIZE;
    }
}