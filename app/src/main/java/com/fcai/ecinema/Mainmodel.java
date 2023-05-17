package com.fcai.ecinema;

import android.net.Uri;

import java.net.URI;

public class Mainmodel {
 Uri movlogo;
 String movname;


 public Mainmodel(Uri movlogo,String movname)
 {
  this.movlogo=movlogo;
  this.movname=movname;

 }

 public Uri getMovlogo() {
  return movlogo;
 }

 public String getMovname() {
  return movname;
 }
}
