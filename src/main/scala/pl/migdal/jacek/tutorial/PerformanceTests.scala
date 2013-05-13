package pl.migdal.jacek.tutorial

import pl.migdal.jacek.scala.hystrix.{PerformanceTestsHelper, DistributedApplication}
import java.util.concurrent.atomic.{AtomicIntegerArray, AtomicInteger}
import scala.concurrent._
import ExecutionContext.Implicits.global
import java.util.concurrent.TimeUnit
import java.util.Date

/**
 * |          I++??Z                                             
 * |        =$?=?=+I+                                           
 * |     ??7$$?O=??I$I      +                                   
 * |   I$I=+$I?ZI7777I+,  ?+$777I                             + 
 * |  7=Z7II?IZI$OOOZ$7I+$7$7II7+~I?I7$                       .  
 * | ~7?7I7Z?Z7?I778$7ZO?7$Z?7?+???7$++I?$                   +   
 * |  $$Z$   77?7OZII7ZZZ$$Z7ZI????$77+7Z?=$                I    
 * |        II7Z+7?+?I7ZZ$7?ZZ$$7$$$?77IZ77I=Z+         I7      
 * |         =I       +7$$7+I7ZZZZZZ7I77IZ$$ZZ7?7?+=?~IZZ        
 * |        Z+~        Z7IIZI+I8I?I$I7=$7$$ZZ$Z$$ZZ$Z$+          
 * |       ~=?          777$77=I$?77Z7$?$7                       
 * |                     Z.II?777$Z7$:I7Z7                       
 * |                     $ 7Z8O+$IZ77 II?$7                      
 * |                     I   $7=Z I7  7III?,                     
 * |                           $7      7$77                      
 * |                           I$7       IZ                      
 * |                         =$ZI        ?$I                     
 * |                        IZ$I         I77                     
 * |                        $7$          ?I7                     
 * |                        Z I         ZO$Z                     
 * |                       $.         ZZ$7I+7           
 * @author Jacek Migdal (jacek@sumologic.com)
 */
object PerformanceTests extends PerformanceTestsHelper {
  def main(args: Array[String]) {
    runTest("naive", new NaiveWay)
  }
}
