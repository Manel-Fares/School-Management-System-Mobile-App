/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

/**
 *
 * @author Neifos
 */

    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.codename1.charts.ChartComponent;
import com.codename1.charts.renderers.XYMultipleSeriesRenderer;
import com.codename1.charts.renderers.XYSeriesRenderer;
import com.codename1.charts.views.BarChart;
import com.codename1.charts.views.BarChart.Type;
import com.codename1.ui.Component;
import com.codename1.ui.Form;
import java.util.ArrayList;
import java.util.List;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.myapp.Services.ServiceBook;
import com.mycompany.myapp.Services.ServiceBooking;
import com.mycompany.myapp.entities.Booking;
import com.mycompany.myapp.entities.Books;






/**
 * Sales demo bar chart.
 */
public class MetricsStackedBarChart extends AbstractDemoChart {
  /**
   * Returns the chart name.
   * 
   * @return the chart name
   */
  public String getName() {
    return "Metrics stacked bar chart";
  }

  /**
   * Returns the chart description.
   * 
   * @return the chart description
   */
  public String getDesc() {
    return "System Health and Compliance";
  }

  /**
   * Executes the chart demo.
   * 
   * @param context the context
   * @return the built intent
   */
  public Form execute()  {
       
    String[] titles = new String[] { "Total Books",/* "Gnr Pvt", "Conc.",*/ "Nombre Booking"/*, "VA", "S3"*/};
    int k=0,j=0,ii=0,a=0;
      System.out.println(ServiceBook.getInstance().getAllBooks());
      for (Books b : ServiceBook.getInstance().getAllBooks()) {
          //titles=new String[]{b.getCategoriebook()};
          if(b.getEtatbook().compareTo("Disponible")==0){
          if(b.getCategoriebook().compareTo("C++")==0)
              k++;
          if(b.getCategoriebook().compareTo("C")==0)
              j++;
          if(b.getCategoriebook().compareTo("Java")==0)
              ii++;
          if(b.getCategoriebook().compareTo("html")==0)
              a++;
          }
          
      }
         int kk=0,jj=0,iii=0,aa=0;
         for (Booking bb : ServiceBooking.getInstance().getAllBooking()) {
             System.out.println(bb);
          if(bb.getBook().getEtatbook().compareTo("Disponible")==0){
          if(bb.getBook().getCategoriebook().compareTo("C++")==0)
              kk++;
          if(bb.getBook().getCategoriebook().compareTo("C")==0)
              jj++;
          if(bb.getBook().getCategoriebook().compareTo("Java")==0)
              iii++;
          if(bb.getBook().getCategoriebook().compareTo("html")==0)
              aa++;
          }
          
      }
      
    List<double[]> values = new ArrayList<double[]>();
    values.add(new double[] { k, j, ii, a}); // Gnrl
  //  values.add(new double[] { 9, 32, 10, 35}); // Gnr Pvt
   // values.add(new double[] { 12, 21, 20, 10}); // Conc.
    values.add(new double[] { kk, jj, iii, aa}); // Pvt
  // values.add(new double[] { 21, 12, 22, 23}); // VA
    //values.add(new double[] { 11, 12, 20, 21}); // S3
    int[] colors = new int[]{ColorUtil.rgb(0, 76, 153), ColorUtil.rgb(0, 102, 204), ColorUtil.rgb(0, 128, 255), ColorUtil.rgb(51, 153, 255)
                            , ColorUtil.rgb(102, 178, 255), ColorUtil.rgb(153, 204, 255)};
    XYMultipleSeriesRenderer renderer = buildBarRenderer(colors);
    setChartSettings(renderer, "Books Selon Category", "", "", 0.5,
        4.5, 0, 50, ColorUtil.GRAY, ColorUtil.LTGRAY);

    renderer.setXLabels(0);
    renderer.setYLabels(0);
    renderer.setXLabelsAlign(Component.LEFT);
    renderer.setYLabelsAlign(Component.LEFT);
    renderer.setPanEnabled(true, false);
    // renderer.setZoomEnabled(false);
    renderer.setZoomRate(1.1f);
    renderer.setBarSpacing(0.5f);
      renderer.setApplyBackgroundColor(true);
      renderer.setBackgroundColor(ColorUtil.WHITE);
      renderer.setMarginsColor(ColorUtil.WHITE);
      renderer.setBarWidth(70);
      renderer.addXTextLabel(1, "C++");
      renderer.addXTextLabel(2, "C");
      renderer.addXTextLabel(3, "Java");
      renderer.addXTextLabel(4, "Html");
//      renderer.setChartTitleTextSize(50);
//      renderer.setChartTitleTextSize(50);
//      renderer.setOrientation(XYMultipleSeriesRenderer.Orientation.HORIZONTAL);
      int length = renderer.getSeriesRendererCount();
     // System.out.println("Series len "+length);
      for (int i = 0; i < length; i++) {
          XYSeriesRenderer seriesRenderer = (XYSeriesRenderer) renderer.getSeriesRendererAt(i);
          seriesRenderer.setDisplayChartValues(true);
          seriesRenderer.setChartValuesTextFont(largeFont);
//            seriesRenderer.setChartValuesFormat(new ICRNumerFormat());
      }
      
      BarChart chart = new BarChart(buildBarDataset(titles, values), renderer,
        Type.STACKED);
      return wrap("", new ChartComponent(chart));
   
  }

}
    

