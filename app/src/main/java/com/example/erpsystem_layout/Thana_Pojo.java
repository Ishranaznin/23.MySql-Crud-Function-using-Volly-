package com.example.erpsystem_layout;

public class Thana_Pojo {
    private int thana_id;
    private String m_thanaName;
    private String m_MemberIDThana ;
    public Thana_Pojo(int thana_id, String m_thanaName, String m_MemberIDThana) {
        this.thana_id = thana_id;
        this.m_thanaName = m_thanaName;
        this.m_MemberIDThana = m_MemberIDThana;
    }

    public int getThana_id() {
        return thana_id;
    }

    public void setThana_id(int thana_id) {
        this.thana_id = thana_id;
    }

    public String getM_thanaName() {
        return m_thanaName;
    }

    public void setM_thanaName(String m_thanaName) {
        this.m_thanaName = m_thanaName;
    }

    public String getM_MemberIDThana() {
        return m_MemberIDThana;
    }

    public void setM_MemberIDThana(String m_MemberIDThana) {
        this.m_MemberIDThana = m_MemberIDThana;
    }


}
