package com.example.erpsystem_layout;

public class Country_Pojo {
    private int country_id;
    private String m_countryName;
    public Country_Pojo(int country_id, String m_countryName, String m_MemberIDcountry) {
        this.country_id = country_id;
        this.m_countryName = m_countryName;
        this.m_MemberIDcountry = m_MemberIDcountry;
    }



    public int getCountry_id() {
        return country_id;
    }

    public String getM_countryName() {
        return m_countryName;
    }

    public String getM_MemberIDcountry() {
        return m_MemberIDcountry;
    }

    private String m_MemberIDcountry ;


}
