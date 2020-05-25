package com.mycompany.myapp.gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Services.UserService;
import com.mycompany.myapp.entities.User;
import java.util.ArrayList;


public class statUserForm extends SideMenuAdminForm {

    public ArrayList<User> users;
    double etd = 0, ens = 0, personnel = 0, admin = 0;

    public statUserForm(Resources res) {
        super(BoxLayout.y());
        Toolbar tb = getToolbar();

        UserService us = new UserService();
        users = us.getInstance().getAllUsers();
        if (!users.isEmpty()) {

            for (User obj : users) {
                if (obj.getRoleUser().equals("[ROLE_ENSEIGNANT]")) {
                    ens++;
                } else if (obj.getRoleUser().equals("[ROLE_ETUDIANT]")) {
                    etd++;
                } else if (obj.getRoleUser().equals("[ROLE_PERSONNEL]")) {
                    personnel++;
                } else {
                    admin++;
                }

            }
        }
        System.out.println("nombre admin" + admin);
        System.out.println("nombre enseignant" + ens);
        double val1 = (double) Math.round(etd / users.size() * 10000) / 100;
        double val2 = (double) Math.round(ens / users.size() * 10000) / 100;
        double val3 = (double) Math.round(personnel / users.size() * 10000) / 100;
        double val4 = (double) Math.round(admin / users.size() * 10000) / 100;
        double[] values = new double[]{val1, val2, val3, val4};
        // Set up the renderer
        int[] colors = new int[]{ColorUtil.rgb(0, 0, 102), ColorUtil.rgb(255, 0, 127), ColorUtil.rgb(0, 128, 255), ColorUtil.rgb(255, 128, 0)};
        DefaultRenderer renderer = buildCategoryRenderer(colors);
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(60);
        renderer.setDisplayValues(true);
        renderer.setDisplayValues(true);
        renderer.setShowLabels(true);
        // renderer.setLabelsTextSize(50);
        //renderer.setLegendTextSize(50);
        Font ft = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
        renderer.setLegendTextFont(ft);
        renderer.setLabelsTextFont(ft);

        // Create the chart ... pass the values and renderer to the chart object.
        PieChart chart = new PieChart(buildCategoryDataset("Users Statistics", values), renderer);

        // Wrap the chart in a Component so we can add it to a form
        ChartComponent c = new ChartComponent(chart);

        tb.setTitleCentered(false);

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Container titleCmp = BoxLayout.encloseX(
                FlowLayout.encloseIn(menuButton), FlowLayout.encloseIn(
                new Label("Users Statistics", "Title")
        )
        );

        tb.setTitleComponent(titleCmp);
        setupSideMenu(res);
        add(c);
    }

    private void addButtonBottom(Image arrowDown, String text, int color, boolean first) {
        MultiButton finishLandingPage = new MultiButton(text);
        finishLandingPage.setEmblem(arrowDown);
        finishLandingPage.setUIID("Container");
        finishLandingPage.setUIIDLine1("TodayEntry");
        finishLandingPage.setIcon(createCircleLine(color, finishLandingPage.getPreferredH(), first));
        finishLandingPage.setIconUIID("Container");
        add(FlowLayout.encloseIn(finishLandingPage));
    }

    private Image createCircleLine(int color, int height, boolean first) {
        Image img = Image.createImage(height, height, 0);
        Graphics g = img.getGraphics();
        g.setAntiAliased(true);
        g.setColor(0xcccccc);
        int y = 0;
        if (first) {
            y = height / 6 + 1;
        }
        g.drawLine(height / 2, y, height / 2, height);
        g.drawLine(height / 2 - 1, y, height / 2 - 1, height);
        g.setColor(color);
        g.fillArc(height / 2 - height / 4, height / 6, height / 2, height / 2, 0, 360);
        return img;
    }

    /**
     * Builds a category series using the provided values.
     *
     * @param titles the series titles
     * @param values the values
     * @return the category series
     */
    protected CategorySeries buildCategoryDataset(String title, double[] values) {
        CategorySeries series = new CategorySeries(title);

        series.add("Student ", values[0]);
        series.add("Teacher ", values[1]);
        series.add("Staff ", values[2]);
        series.add("Administrator ", values[3]);

        return series;
    }

    private DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(15);
        renderer.setLegendTextSize(15);
        renderer.setMargins(new int[]{20, 30, 15, 0});
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }

}
