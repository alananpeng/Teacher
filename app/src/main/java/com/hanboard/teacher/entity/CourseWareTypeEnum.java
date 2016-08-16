package com.hanboard.teacher.entity;

/**
 *
 * 教案资源类型
 *
 * Created by Administrator on 2016/7/20 0020.
 */
public enum CourseWareTypeEnum {

    PPT(new Integer(1).byteValue(),"PPT"),
    WORD(new Integer(2).byteValue(),"WORD"),
    TXT(new Integer(3).byteValue(),"TXT"),
    EXCEL(new Integer(4).byteValue(),"EXCEL"),
    VIDEO(new Integer(5).byteValue(),"VIDEO"),
    AUDIO(new Integer(6).byteValue(),"AUDIO"),
    PIC(new Integer(7).byteValue(),"PIC"),
    PDF(new Integer(8).byteValue(),"PDF");

    private byte id;

    private String message;

    public byte getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    CourseWareTypeEnum(byte id, String message) {
        this.id = id;
        this.message = message;
    }

    public static byte getTypeId(String message){
        for (CourseWareTypeEnum type : CourseWareTypeEnum.values()) {
            if (type.getMessage().equals(message)) {
                return type.getId();
            }
        }
        return 0;

    }

}
