package day07

import java.sql.{Connection, DriverManager, PreparedStatement}

/**
  * Created by zx on 2017/12/12.
  */
object MyUtils {

  def ip2Long(ip:String):Long ={
    val fragments = ip.split("[.]")
    var ipNum =0L
    for(i<- 0 until fragments.length){
      ipNum = fragments(i).toLong | ipNum << 8L
    }
    ipNum
  }




  def binarySearch(lines: Array[(Long,Long,String)],ip: Long):Int ={
    var low =0
    var high =lines.length-1
    while(low <=high){
      val middle =(low+high)/2
      if((ip>=lines(middle)._1) && (ip<=lines(middle)._2))
        return middle
      if(ip < lines(middle)._1)
        high=middle -1
      else{
        low =middle +1
      }
    }
    -1
  }

  def data2MySQL(iter:Iterator[(String,Int)])={
    val conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","123456")
    val ps = conn.prepareStatement("insert into access_log values (?,?)")
    iter.foreach(x =>{
      ps.setString(1,x._1)
      ps.setInt(2,x._2)
      ps.executeUpdate()
    })
    if(conn!=null){
      conn.close()
    }
    if(ps!=null){
      ps.close()
    }
  }

}
