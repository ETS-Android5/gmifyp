package com.example.gmifyp;

public class Users {

    String ic, matrix, email, password;

    public Users() {

    }

    public Users(String ic, String matrix, String email, String password) {
        this.ic = ic;
        this.matrix = matrix;
        this.email = email;
        this.password = password;
    }

    public String getIc() {
        return ic;
    }

    public void setIc(String ic) {
        this.ic = ic;
    }

    public String getMatrix() {
        return matrix;
    }

    public void setMatrix(String matrix) {
        this.matrix = matrix;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}