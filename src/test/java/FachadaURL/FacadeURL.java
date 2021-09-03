package FachadaURL;

public class FacadeURL {

    private URL url;

    public FacadeURL(String Dominio, String token, String category, String id, String subCategory, Boolean newAtribute, Boolean validateWithlogin , String apiKey, String session_id){

        url = new URL();
        url.setDominio(Dominio);
        url.setToken(token);
        url.setCategory(category);
        url.setId(id);
        url.setSubCategory(subCategory);
        url.setNewAtribute(newAtribute);
        url.setValidateWithLogin(validateWithlogin);
        url.setApiKey(apiKey);
        url.setSession_id(session_id);
        System.out.println(url.getSession_id());

    }

    public String construirURLFaca() { return url.construirURL();}

}
