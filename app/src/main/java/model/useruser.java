package model;

import android.os.Parcel;
import android.os.Parcelable;

public class useruser implements Parcelable {

    private int id;
    private String name, address;
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public useruser(String name, int age, String address){
        this.name = name;
        this.age = age;
        this.address = address;
    }

    protected useruser(Parcel in) {
        id = in.readInt();
        name = in.readString();
        age = in.readInt();
        address = in.readString();
    }

    public static final Creator<useruser> CREATOR = new Creator<useruser>() {
        @Override
        public useruser createFromParcel(Parcel in) {
            return new useruser(in);
        }

        @Override
        public useruser[] newArray(int size) {
            return new useruser[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(age);
        dest.writeString(address);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
