package com.example.erpsystem_layout;

public class District_Pojo {
    private int district_id;

    public int getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(int district_id) {
        this.district_id = district_id;
    }

    public String getM_districtName() {
        return m_districtName;
    }

    public void setM_districtName(String m_districtName) {
        this.m_districtName = m_districtName;
    }

    public String getM_MemberIDdistrict() {
        return m_MemberIDdistrict;
    }

    public void setM_MemberIDdistrict(String m_MemberIDdistrict) {
        this.m_MemberIDdistrict = m_MemberIDdistrict;
    }

    private String m_districtName;
    private String m_MemberIDdistrict ;
    public District_Pojo(int district_id, String m_districtName, String m_MemberIDdistrict) {
        this.district_id = district_id;
        this.m_districtName = m_districtName;
        this.m_MemberIDdistrict = m_MemberIDdistrict;
    }


}
