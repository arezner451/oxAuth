package org.xdi.oxauth.model.authorize;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.xdi.oxauth.model.util.Util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Javier Rojas Blum Date: 03.09.2012
 */
public class IdTokenMember {

    private List<Claim> claims;
    private Integer maxAge;

    public IdTokenMember(JSONObject jsonObject) throws JSONException {
        claims = new ArrayList<Claim>();
        if (jsonObject.has("claims")) {
            JSONObject claimsJsonObject = jsonObject.getJSONObject("claims");

            for (Iterator<String> iterator = claimsJsonObject.keys(); iterator.hasNext(); ) {
                String claimName = iterator.next();
                ClaimValue claimValue = null;

                if (claimsJsonObject.isNull(claimName)) {
                    claimValue = ClaimValue.createNull();
                } else {
                    JSONObject claimValueJsonObject = claimsJsonObject.getJSONObject(claimName);
                    if (claimValueJsonObject.has("essential")) {
                        boolean essential = claimValueJsonObject.getBoolean("essential");
                        claimValue = ClaimValue.createEssential(essential);
                    } else if (claimValueJsonObject.has("values")) {
                        JSONArray claimValueJsonArray = claimValueJsonObject.getJSONArray("values");
                        List<String> claimValueArr = Util.asList(claimValueJsonArray);
                        claimValue = ClaimValue.createValueList(claimValueArr);
                    } else if (claimValueJsonObject.has("value")) {
                        String value = claimValueJsonObject.getString("value");
                        claimValue = ClaimValue.createSingleValue(value);
                    }
                }

                Claim claim = new Claim(claimName, claimValue);
                claims.add(claim);
            }
        }
        if (jsonObject.has("max_age")) {
            maxAge = jsonObject.getInt("max_age");
        }
    }

    public List<Claim> getClaims() {
        return claims;
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public Claim getClaim(String claimName) {
        if (StringUtils.isNotBlank(claimName)) {
            for (Claim claim : claims) {
                if (claimName.equals(claim.getName())) {
                    return claim;
                }
            }
        }

        return null;
    }
}