package FachadaURL;

public class FachadaURL {

    private URL url;

    public FachadaURL(String Dominio, String token, String category, String id, String subCategory, Boolean newAtribute,Boolean validateWithlogin ,  String apiKey){

        url = new URL();
        url.setDominio(Dominio);
        url.setToken(token);
        url.setCategory(category);
        url.setId(id);
        url.setSubCategory(subCategory);
        url.setNewAtribute(newAtribute);
        url.setValidateWithLogin(validateWithlogin);
        url.setApiKey(apiKey);

    }

    public String construirURLFach() { return url.construirURL();}

}
