/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guessnum;

/**
 *
 * @author ROG15
 */
import java.io.Serializable;

public abstract class PowerUp implements Serializable {
    protected String name;

    public abstract void use(Game game, Player player);

    public String getName() {
        return name;
    }
}
