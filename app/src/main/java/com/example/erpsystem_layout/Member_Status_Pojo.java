package com.example.erpsystem_layout;

public class Member_Status_Pojo {

    private int m_id;

    public int getM_id() {
        return m_id;
    }

    public String getM_statusName() {
        return m_statusName;
    }

    public String getM_MemberID() {
        return m_MemberID;
    }

    private String m_statusName;
    private String m_MemberID ;

    public Member_Status_Pojo(int m_id, String m_statusName, String m_MemberID) {
        this.m_id = m_id;
        this.m_statusName = m_statusName;
        this.m_MemberID = m_MemberID;
    }




}


