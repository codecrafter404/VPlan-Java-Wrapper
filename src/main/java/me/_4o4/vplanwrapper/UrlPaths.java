package me._4o4.vplanwrapper;

public enum UrlPaths {
    GET_DAY ("/vplan/api/vplan5.php"),
    CHECK_PASSWORD ("/vplan/api/vplan5.php");

    private String path;
    UrlPaths(String x){
        this.path = x;
    }
    public String getPath(){
        return this.path;
    }
}
