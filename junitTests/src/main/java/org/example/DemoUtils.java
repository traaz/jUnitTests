package org.example;

public class DemoUtils {

    private String kelime = "Ali";
    private String ikinciKelime = kelime;
    public int add(int a, int b){
        return a+b;
    }
    public String kelimeGetir(){
        return kelime;
    }
    public String ikinciKelimeGetir(){
        return ikinciKelime;
    }
    public Boolean isGreater(int a , int b){
        if( a> b){
            return true;
        }
        return false;
    }

    public String throwException(int a) throws Exception{
        if(a<0){
            throw new Exception("Value should be greater than or equal to 0");
        }
        return "Value is greater than or equal to 0";
    }
}
