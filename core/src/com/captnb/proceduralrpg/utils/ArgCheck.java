package com.captnb.proceduralrpg.utils;

final public class ArgCheck
{
  
  static public void notNull(Object obj)
  {
    if(obj == null)
      throw new IllegalArgumentException("Argument can't be null!");
  }
  
  static public void notEmpty(String string)
  {
    if("".equals(string))
      throw new IllegalArgumentException("String can't be empty!");
  }
  
  static public void inRange(int number, int min, int max)
  {
    if(number < min || number > max)
      throw new IllegalArgumentException(number + " is outside of range [" + min + ", " + max + "]!");
  }
  
  static public void isPositive(int number)
  {
    if(number < 0)
      throw new IllegalArgumentException("Number must be positive!");
  }
  
  static public void isExclusivelyPositive(int number)
  {
    if(number <= 0)
      throw new IllegalArgumentException("Number must be exclusively positive!");
  }
  
  static public void isNegative(int number)
  {
    if(number > 0)
      throw new IllegalArgumentException("Number must be negative!");
  }
  
  static public void isExclusivelyNegative(int number)
  {
    if(number >= 0)
      throw new IllegalArgumentException("Number must be exclusively negative!");
  }
  
  static public void inRange(double number, double min, double max)
  {
    if(number < min || number > max)
      throw new IllegalArgumentException(number + " is outside of range [" + min + ", " + max + "]!");
  }
  
  static public void isPositive(double number)
  {
    if(number < 0.0)
      throw new IllegalArgumentException("Floating number must be positive!");
  }
  
  static public void isNegative(double number)
  {
    if(number > 0.0)
      throw new IllegalArgumentException("Floating number must be negative!");
  }
  
  static public void isNot(Object obj, Object isnot)
  {
    if(obj.equals(isnot))
      throw new IllegalArgumentException(isnot.toString() + " is not allowed!");
  }
  
  private ArgCheck(){};
}