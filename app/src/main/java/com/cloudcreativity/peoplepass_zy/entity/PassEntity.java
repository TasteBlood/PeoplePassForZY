package com.cloudcreativity.peoplepass_zy.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class PassEntity implements Parcelable {

        private int areaId;
        private int cityId;
        private String content;
        private String createTime;
        private String detailAddress;
        private int id;
        private int provinceId;
        private String reImgs;
        private int state;
        private int streetId;
        private String title;
        private int type;
        private String updateTime;
        private long userId;
        private String voiceFile;
        private String actualName;
        private String idCard;
        private String phoneNumber;

    protected PassEntity(Parcel in) {
        areaId = in.readInt();
        cityId = in.readInt();
        content = in.readString();
        createTime = in.readString();
        detailAddress = in.readString();
        id = in.readInt();
        provinceId = in.readInt();
        reImgs = in.readString();
        state = in.readInt();
        streetId = in.readInt();
        title = in.readString();
        type = in.readInt();
        updateTime = in.readString();
        userId = in.readLong();
        voiceFile = in.readString();
        actualName = in.readString();
        idCard = in.readString();
        phoneNumber = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(areaId);
        dest.writeInt(cityId);
        dest.writeString(content);
        dest.writeString(createTime);
        dest.writeString(detailAddress);
        dest.writeInt(id);
        dest.writeInt(provinceId);
        dest.writeString(reImgs);
        dest.writeInt(state);
        dest.writeInt(streetId);
        dest.writeString(title);
        dest.writeInt(type);
        dest.writeString(updateTime);
        dest.writeLong(userId);
        dest.writeString(voiceFile);
        dest.writeString(actualName);
        dest.writeString(idCard);
        dest.writeString(phoneNumber);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PassEntity> CREATOR = new Creator<PassEntity>() {
        @Override
        public PassEntity createFromParcel(Parcel in) {
            return new PassEntity(in);
        }

        @Override
        public PassEntity[] newArray(int size) {
            return new PassEntity[size];
        }
    };

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public String getReImgs() {
        return reImgs;
    }

    public void setReImgs(String reImgs) {
        this.reImgs = reImgs;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getStreetId() {
        return streetId;
    }

    public void setStreetId(int streetId) {
        this.streetId = streetId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getVoiceFile() {
        return voiceFile;
    }

    public void setVoiceFile(String voiceFile) {
        this.voiceFile = voiceFile;
    }

    public String getActualName() {
        return actualName;
    }

    public void setActualName(String actualName) {
        this.actualName = actualName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String formatTime(){
        return this.createTime.substring(0,this.createTime.length()-2);
    }
}
