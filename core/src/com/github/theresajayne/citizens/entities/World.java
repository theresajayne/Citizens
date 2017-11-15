package com.github.theresajayne.citizens.entities;

public class World
{
    private int[][] world;

    public World()
    {
        this.world = new int[10][10];
        for(int x = 0;x<10;x++)
            for(int y = 0;y<10;y++)
            {
                this.world[x][y] = 5;
            }
            this.world[4][4] = 6;
    }

    public int getHeightAt(int x, int y)
    {
        return this.world[x][y];
    }

    public void setHeightAt(int x, int y,int height)
    {
        this.world[x][y] = height;
    }
}
