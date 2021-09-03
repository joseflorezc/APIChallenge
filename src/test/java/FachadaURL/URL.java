package FachadaURL;

public class URL {


    private String dominio;
    private String token;
    private String category;
    private String id;
    private String subCategory;
    private String apiKey;
    private Boolean newAtribute = false;
    private Boolean validateWithLogin = false;
    private String session_id;

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }



    public Boolean isNewAtribute() {
        return newAtribute;
    }

    public void setNewAtribute(boolean newAtribute) {
        this.newAtribute = newAtribute;
    }
    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getDominio() {
        return dominio;
    }

    public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public Boolean getValidateWithLogin() {
        return validateWithLogin;
    }

    public void setValidateWithLogin(Boolean validateWithLogin) {
        this.validateWithLogin = validateWithLogin;
    }

    public String construirURL(){
        String url = "";

        url = ""+ dominio;
        url = url + "/"+category;

        if(!token.equals("") ){
            url = url +"/"+token;
        }
        if(!id.equals("")){
            url = url + "/" + id;
        }
        if(!subCategory.equals("")){
            url = url + "/" + subCategory;
        }
        if(newAtribute){
            url = url + "/new";
        }
        if(validateWithLogin){
            url = url + "/validate_with_login";
        }


        url = url + "?api_key="+apiKey;

        if (!session_id.equals("")){
            url = url + "&session_id="+session_id;
        }


        return url;
    }


}
