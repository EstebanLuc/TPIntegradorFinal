/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Luciano
 */
public class Productos {
    private int codigo;
    private String item;
    private String modelo;
    private String marca;
    private double precio;    
    private int stock;

    public Productos(int codigo, String item, String modelo, String marca, double precio,  int stock) {
        this.codigo = codigo;
        this.item = item;
        this.modelo = modelo;
        this.marca = marca;
        this.precio = precio;        
        this.stock = stock;
    }

    public Productos(String item, String modelo, String marca, double precio, int stock) {
        this.item = item;
        this.modelo = modelo;
        this.marca = marca;
        this.precio = precio;        
        this.stock = stock;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getItem() {
        return item;
    }

    public void setIroducto(String item) {
        this.item = item;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }  

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
         return "Hard{" + "item=" + item + ", marca=" + marca + ", modelo=" + modelo + ", precio=" + precio + ", stock=" + stock + '}';
    }
    
    
}
