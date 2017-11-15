package com.github.theresajayne.citizens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.IntAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.github.theresajayne.citizens.entities.World;

import java.util.Random;

public class Citizens extends ApplicationAdapter
{
    ModelBatch batch;
    ModelInstance instance;
    World world = new World();
    PerspectiveCamera cam;

    @Override
    public void create() {
        batch = new ModelBatch();
        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(20f, 20f, 20f);
        cam.lookAt(0, 0, 0);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();
        ModelBuilder modelBuilder = new ModelBuilder();
//		model = modelBuilder.createBox(5f,5f,5f,new Material(ColorAttribute.createDiffuse(Color.GREEN)), VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        instance = processMap();//new ModelInstance(model);
    }

    private ModelInstance processMap()
    {
        Model model;
        Model tempModel = new Model();
        ModelBuilder modelBuilder = new ModelBuilder();

        for(int x = 0;x<9;x++)
            for(int y=0;y<9;y++)
            {
                model = modelBuilder.createRect(x,  world.getHeightAt(x, y),y,
                        x + 1,  world.getHeightAt(x + 1, y),y ,
                        x + 1,  world.getHeightAt(x + 1, y + 1),y + 1,
                        x,  world.getHeightAt(x, y + 1),y + 1,
                        0, 1, 0,
                        GL20.GL_TRIANGLES,
                        new Material(ColorAttribute.createDiffuse(Color.YELLOW), new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA)), VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal );
                tempModel.nodes.addAll(model.nodes);
            }

        return new ModelInstance(tempModel);
    }

    @Override
    public void render() {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT |GL20.GL_NONE);

        batch.begin(cam);
        batch.render(instance);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
