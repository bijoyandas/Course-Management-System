package com.College;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import java.awt.Color;
import java.awt.Toolkit; 


public class Chart extends JFrame {

	
	Connection myCon = null;
	ResultSet myRs = null;
	PreparedStatement mySmt = null;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Chart( String applicationTitle , String chartTitle ) throws SQLException {
		super( applicationTitle );
		setType(javax.swing.JFrame.Type.UTILITY);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Chart.class.getResource("/com/College/1481642427_college.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
			myCon = MyConnection.getConnection();
			mySmt = myCon.prepareStatement("select * from Course");
			myRs = mySmt.executeQuery();
		}
		catch(Exception e) {}
	      JFreeChart barChart = ChartFactory.createBarChart(
	         chartTitle,           
	         "Courses",            
	         "Number of Students",            
	         createDataset(),          
	         PlotOrientation.VERTICAL,           
	         true, true, false);
	         
	      ChartPanel chartPanel = new ChartPanel( barChart );     
	      chartPanel.setForeground(new Color(255, 255, 255));
	      chartPanel.setBackground(new Color(32, 178, 170));
	      chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 400 ) );        
	      setContentPane( chartPanel ); 
	}
	private CategoryDataset createDataset( ) throws SQLException
	   {                
	      final String student = "";               
	      final DefaultCategoryDataset dataset = 
	      new DefaultCategoryDataset( );  
	      
	      while(myRs.next()) {
	      dataset.addValue( Integer.parseInt(myRs.getString("Students")) , myRs.getString("Name") , student );                               
	      }
	      return dataset; 
	   }
}
