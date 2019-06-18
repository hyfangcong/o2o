package com.imooc.cong.o2o.enums;

/**
 * @author: fangcong
 * @date: 2019/6/9
 */
public enum ShopStateEnum {
    CHECK(0,"审核中"), OFFLINE(-1, "非法店铺"), SUCCESS(1, "操作成功"),
    INNER_ERROR(10001, "内部系统错误"), NULL_SHOPID(10002, "店铺id为空"),
    NULL_SHOP(1003, "店铺信息为空");

    private int state;
    private String stateInfo;

    ShopStateEnum(int state, String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }

    /**
     * 更据state获取ShopStateEnum
     * @param state
     * @return
     */
    public ShopStateEnum stateOf(int state){
        for(ShopStateEnum stateEnum : values()){
            if(stateEnum.state == state){
                return stateEnum;
            }
        }
        return null;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }}
