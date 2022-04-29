/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hkmu.comps380f.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pollingresult")
public class PollingResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String question;
    private int totalchoiceA;
    private int totalchoiceB;
    private int totalchoiceC;
    private int totalchoiceD;

    public PollingResult() {
    }

    public PollingResult(String question, int totalchoiceA, int totalchoiceB, int totalchoiceC, int totalchoiceD) {
        this.question = question;
        this.totalchoiceA = totalchoiceA;
        this.totalchoiceB = totalchoiceB;
        this.totalchoiceC = totalchoiceC;
        this.totalchoiceD = totalchoiceD;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getTotalchoiceA() {
        return totalchoiceA;
    }

    public void setTotalchoiceA(int totalchoiceA) {
        this.totalchoiceA = totalchoiceA;
    }

    public int getTotalchoiceB() {
        return totalchoiceB;
    }

    public void setTotalchoiceB(int totalchoiceB) {
        this.totalchoiceB = totalchoiceB;
    }

    public int getTotalchoiceC() {
        return totalchoiceC;
    }

    public void setTotalchoiceC(int totalchoiceC) {
        this.totalchoiceC = totalchoiceC;
    }

    public int getTotalchoiceD() {
        return totalchoiceD;
    }

    public void setTotalchoiceD(int totalchoiceD) {
        this.totalchoiceD = totalchoiceD;
    }

}
