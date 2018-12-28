package android.kanzz.com.myshop.bean;

public class Tab {

    private int title;
    private int icon;
    private Class fragnment;

    public Tab(Class fragnment,int title, int icon) {
        this.title = title;
        this.icon = icon;
        this.fragnment = fragnment;
    }



    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public Class getFragnment() {
        return fragnment;
    }

    public void setFragnment(Class fragnment) {
        this.fragnment = fragnment;
    }


}
