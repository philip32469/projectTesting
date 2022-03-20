package hkmu.comps380f.model;

import java.io.Serializable;

public class Attachment implements Serializable {
    private String name;
    private String mimeContentType;
    private byte[] contents;

    // Getters and Setters of name, mimeContentType, contents
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMimeContentType() {
        return mimeContentType;
    }

    public void setMimeContentType(String mimeContentType) {
        this.mimeContentType = mimeContentType;
    }

    public byte[] getContents() {
        return contents;
    }

    public void setContents(byte[] contents) {
        this.contents = contents;
    }

}

