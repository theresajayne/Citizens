package com.github.theresajayne.citizens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.IntAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.github.theresajayne.citizens.entities.World;

import java.util.Random;

public class Citizens extends ApplicationAdapter
{
    ModelBatch batch;
    ModelInstance instance;
    World world = new World();
    PerspectiveCamera cam;
    Environment environment;
    CameraInputController camController;
    Texture texture;

    @Override
    public void create() {
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight,0.4f,0.4f,0.4f,1f));
        environment.add(new DirectionalLight().set(0.8f,0.8f,0.8f,-1f,-0.8f,-0.2f));
        batch = new ModelBatch();
        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(20f, 20f, 7f);
        cam.lookAt(5, 5, 5);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();
        camController = new CameraInputController(cam);
        Gdx.input.setInputProcessor(camController);
        ModelBuilder modelBuilder = new ModelBuilder();
//		model = modelBuilder.createBox(5f,5f,5f,new Material(ColorAttribute.createDiffuse(Color.GREEN)), VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        instance = processMap();//new ModelInstance(model);
        FileHandle imageFileHandle = Gdx.files.internal("dirt.png");
        texture = new Texture(imageFileHandle);
    }

    private ModelInstance processMap()
    {
        Model model;
        Model tempModel = new Model();
        ModelBuilder modelBuilder = new ModelBuilder();
        Texture texture;

        for(int x = 0;x<9;x++)
            for(int y=0;y<9;y++)
            {
                model = modelBuilder.createRect(x + 1, world.getHeightAt(x + 1, y + 1), y + 1,
                        x + 1,  world.getHeightAt(x + 1, y),y ,

                        x,  world.getHeightAt(x, y),y,
                        x,  world.getHeightAt(x, y + 1),y + 1,
                        0, 1, 0,
                        GL20.GL_LINES,
                        new Material(ColorAttribute.createDiffuse(Color.YELLOW), new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA)), VertexAttributes.Usage.Position | VertexAttributes.Usage.ColorPacked | VertexAttributes.Usage.TextureCoordinates| VertexAttributes.Usage.Normal );

                tempModel.nodes.addAll(model.nodes);
            }

        return new ModelInstance(tempModel);
    }

    @Override
    public void render() {
        camController.update();
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT |GL20.GL_NONE);
        Gdx.graphics.getGL20().glEnable(GL20.GL_TEXTURE_2D);
        texture.bind();
        batch.begin(cam);
        Gdx.gl.glCullFace(GL20.GL_NONE);
        batch.render(instance, environment);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
