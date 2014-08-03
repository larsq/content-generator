/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package local.laer.app.newgenerator.domain;

import java.util.UUID;

/**
 *
 * @author Lars Eriksson (larsq.eriksson@gmail.com)
 */
public class SectionData {
    private String id;
    private String headline;
    private String content;

    public SectionData() {
    }

    public SectionData(String id, String headline) {
        this.id = id;
        this.headline = headline;
    }

    public SectionData(String id, String headline, String content) {
        this.id = id;
        this.headline = headline;
        this.content = content;
    }
    
    public void generateId() {
        id = UUID.randomUUID().toString();
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    
}
