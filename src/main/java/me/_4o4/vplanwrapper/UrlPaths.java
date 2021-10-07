package me._4o4.vplanwrapper;

/**
 * This enum contains the url-paths for certain use-cases
 */
public enum UrlPaths {
    GET_DAY ("/vplan/api/vplan5.php"),
    CHECK_PASSWORD ("/vplan/api/vplan5.php");

    private final String path;
    UrlPaths(String x){
        this.path = x;
    }
    public String getPath(){
        return this.path;
    }
}
