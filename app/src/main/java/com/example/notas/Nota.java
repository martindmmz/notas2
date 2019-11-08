package com.example.notas;

public  class Nota {
    int id;
            String titulo;
            String descripcion;
            String contenido;
            String fecha;

    public Nota(int id, String titulo, String descripcion, String contenido, String fecha) {

        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.contenido = contenido;
        this.fecha = fecha;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override

    public String toString(){

        String des = this.descripcion;
        if(this.descripcion.length()>100){
            des = des.substring(0,97);
            des = des+"...";

        }
        return "Título: "+this.titulo+"\n"+
                "Descripción:"+descripcion;

}






}