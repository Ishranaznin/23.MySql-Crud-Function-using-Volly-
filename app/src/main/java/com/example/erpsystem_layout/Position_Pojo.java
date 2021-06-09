package com.example.erpsystem_layout;

public class Position_Pojo {

    private int position_id;
    private String m_positionName;
    private String m_MemberIDposition ;
    public Position_Pojo(int position_id, String m_positionName, String m_MemberIDposition) {
        this.position_id = position_id;
        this.m_positionName = m_positionName;
        this.m_MemberIDposition = m_MemberIDposition;
    }

    public int getPosition_id() {
        return position_id;
    }

    public void setPosition_id(int position_id) {
        this.position_id = position_id;
    }

    public String getM_positionName() {
        return m_positionName;
    }

    public void setM_positionName(String m_positionName) {
        this.m_positionName = m_positionName;
    }

    public String getM_MemberIDposition() {
        return m_MemberIDposition;
    }

    public void setM_MemberIDposition(String m_MemberIDposition) {
        this.m_MemberIDposition = m_MemberIDposition;
    }


}
