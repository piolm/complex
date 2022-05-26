package com.company;

import java.util.Objects;

public class Complex {

    private double notImage, image;

    public Complex() {
        this.notImage = 0;
        this.image = 0;
    }

    public Complex(double real, double imag) {
        this.image = imag;
        this.notImage = real;
    }

    public Complex(Complex c) {
        this.image = c.getImaginary();
        this.notImage = c.getNotImage();
    }

    public double getNotImage(){
        return this.notImage;
    }

    public double getImaginary() {
        return this.image;
    }
    public static Complex add(Complex z1, Complex z2){
        return new Complex(z1.getNotImage()+z2.getNotImage(),
                z1.getImaginary()+z2.getImaginary());
    }

    public void add(Complex z){
        this.notImage += z.getNotImage();
        this.image += z.getImaginary();
    }

    public static Complex subtract(Complex z1, Complex z2){
        return new Complex(z1.getNotImage()-z2.getNotImage(),
                z1.getImaginary()- z2.getImaginary());
    }

    public void subtract(Complex z){
        this.notImage -= z.getNotImage();
        this.image -= z.getImaginary();
    }
    public static Complex multiply(Complex z1, Complex z2){
        return new Complex(z1.getNotImage()*z2.getNotImage() - z1.getImaginary()*z2.getImaginary(),
                z1.getNotImage()*z2.getImaginary() + z2.getNotImage()*z1.getImaginary());
    }

    public void multiply(Complex z){
        this.notImage = this.notImage *z.getNotImage() - this.image * z.getImaginary();
        this.image = this.notImage *z.getImaginary() + this.image *z.getNotImage();
    }

    public static Complex division(Complex z1, Complex z2){
        return new Complex((z1.getNotImage()*z2.getNotImage()+z1.getImaginary()*z2.getImaginary())/(z2.getNotImage() * z2.getNotImage() + z2.getImaginary()*z2.getImaginary()),
                (z1.getImaginary()*z2.getNotImage()-z1.getNotImage()*z2.getImaginary())/(z2.getNotImage() * z2.getNotImage() + z2.getImaginary()*z2.getImaginary()));
    }

    public void division(Complex z){
        this.notImage = (this.notImage *z.getNotImage()+this.image *z.getImaginary()) / (z.getNotImage()*z.getNotImage()+z.getImaginary()*z.getImaginary());
        this.image = (this.image *z.getNotImage()-this.notImage *z.getImaginary()) / (z.getNotImage()*z.getNotImage()+z.getImaginary()*z.getImaginary());
    }

    public double abs(){
        return Math.sqrt(this.image *this.image +this.notImage *this.notImage);
    }

    public Complex conjugate(){
        return new Complex(this.notImage, -this.image);
    }

    public static boolean equals(Complex z1, Complex z2){
        return z1.getNotImage() == z2.getNotImage() && z1.getImaginary() == z2.getImaginary();
    }

    public boolean equals(Complex z){
        return this.notImage == z.getNotImage() && this.image == z.getImaginary();
    }

    public Complex neg(){
        return new Complex(-this.notImage, -this.image);
    }

    @Override
    public String toString() {
        if (this.image > 0){
            return this.notImage +" + "+this.image +"i";
        } else{
            return this.notImage +" - "+Math.abs(this.image)+"i";
        }
    }

    public static Complex parse(String zstring){
        zstring = zstring.replaceAll(" ", "");

        if (zstring.contains(String.valueOf("+")) || (zstring.contains(String.valueOf("-")) && zstring.lastIndexOf('-') > 0)) {
            String re = "";
            String im = "";
            zstring = zstring.replaceAll("i", "");
            if (zstring.indexOf("+") > 0){
                re = zstring.substring(0, zstring.indexOf("+"));
                im = zstring.substring(zstring.indexOf("+")+1, zstring.length());
                return new Complex(Double.parseDouble(re), Double.parseDouble(im));
            } else if (zstring.lastIndexOf("-")>0){                         // if (zstring.contains("-")){
                re = zstring.substring(0, zstring.lastIndexOf("-"));
                im = zstring.substring(zstring.lastIndexOf("-")+1, zstring.length());

                return new Complex(Double.parseDouble(re), -Double.parseDouble(im));
            }
        }
        return new Complex();
    }
    public Complex pow(double n){
        return new Complex(Math.pow(this.abs(), n)*Math.cos(Math.acos(this.notImage /this.abs())*n),
                Math.pow(this.abs(), n)*Math.sin(Math.asin(this.image /this.abs())*n));
    }

    public Complex sqrt(){
        return new Complex(Math.sqrt((this.notImage +this.abs())/2), Math.sqrt((-this.notImage +this.abs())/2));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Complex complex = (Complex) o;
        return Double.compare(complex.notImage, notImage) == 0 && Double.compare(complex.image, image) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(notImage, image);
    }

    public double getArgument(){
        return Math.acos(this.notImage /this.abs());
    }

    public String eString() {
        return this.abs() + " (cos " + this.getArgument() + " + i sin " + this.getArgument() + ")";
    }
    public boolean checkReal(){return this.image == 0;}
    public boolean checkImaginary(){return this.notImage == 0 && this.image != 0;}
}