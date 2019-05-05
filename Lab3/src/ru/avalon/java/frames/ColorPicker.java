package ru.avalon.java.frames;

import ru.avalon.java.ui.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;

public class ColorPicker extends AbstractFrame {

    private JLabel labelRed = new JLabel("Red:");
    private JLabel labelGreen = new JLabel("Green:");
    private JLabel labelBlue = new JLabel("Blue:");
    private JLabel label0 = new JLabel("0");
    private JLabel label255 = new JLabel("255");
    private JLabel labelColor = new JLabel("Цвет: ");
    private JSlider slider = new JSlider();
    private JSlider slider2 = new JSlider();
    private JSlider slider3 = new JSlider();
    private JPanel controlsPanel = new JPanel();
    private JPanel controlsPanel2 = new JPanel();
    private JPanel controlsPanel3 = new JPanel();
    private JPanel allControlPanels = new JPanel();
    private JPanel colorPanel = new JPanel();

    @Override
    protected void onCreate() {

        //Задаем название и размеры окна
        setTitle("Color Picker");
        setSize(450, 350);

        //Задаем минимальные размеры окна, 
        //чтобы слайдеры не перекрывали друг друга
        Dimension dimensionMinimum = new Dimension(450, 350);
        setMinimumSize(dimensionMinimum);

        //Cоздаем панель окна для размещения компонентов
        JPanel panel;

        //Добавляем на панель окна панель colorPanel для отображения цветов
        panel = CreateColorPanel();
        add(panel, BorderLayout.WEST);

        //Cоздаем отступы панели colorPanel от рамки окна
        getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));

        //Добавляем на панель окна панель allControlsPanel, 
        //которая содержит 3 панели со слайдерами
        panel = CreateAllControlPanels();
        add(panel, BorderLayout.EAST);

        //Добавляем обработчики событий слайдеров
        slider.addChangeListener(this::onSliderChange);
        slider2.addChangeListener(this::onSlider2Change);
        slider3.addChangeListener(this::onSlider3Change);

    }
// Метод создаёт панель на окне со слайдером и меткой Red

    private JPanel CreateControlsPanel() {
        controlsPanel.setLayout(new BorderLayout());
        controlsPanel.add(slider, BorderLayout.CENTER);
        controlsPanel.add(labelRed, BorderLayout.LINE_START);

        Border border = BorderFactory.createEmptyBorder(30, 30, 30, 30);
        controlsPanel.setBorder(border);

        return controlsPanel;
    }
// Метод возвращает панель на окне со слайдером и меткой Green

    private JPanel CreateControlsPanel2() {
        controlsPanel2.setLayout(new BorderLayout());
        controlsPanel2.add(slider2, BorderLayout.CENTER);
        controlsPanel2.add(labelGreen, BorderLayout.LINE_START);

        Border border = BorderFactory.createEmptyBorder(30, 30, 30, 30);
        controlsPanel2.setBorder(border);

        return controlsPanel2;
    }
// Метод возвращает панель со слайдером и меткой Blue

    private JPanel CreateControlsPanel3() {
        controlsPanel3.setLayout(new BorderLayout());
        controlsPanel3.add(slider3, BorderLayout.CENTER);
        controlsPanel3.add(labelBlue, BorderLayout.LINE_START);
        controlsPanel3.add(label0, BorderLayout.PAGE_END);

        Border border = BorderFactory.createEmptyBorder(30, 30, 50, 30);
        controlsPanel3.setBorder(border);

        return controlsPanel3;
    }
// Метод возвращает панель со слайдером и меткой Green

    private JPanel CreateColorPanel() {
        colorPanel.setLayout(new BorderLayout());
        Dimension dimension = new Dimension(100, 100);
        colorPanel.setPreferredSize(dimension);
        Border border2 = new TitledBorder("");
        colorPanel.setBorder(border2);
        colorPanel.setSize(100, 100);
        colorPanel.add(labelColor, BorderLayout.CENTER);

        return colorPanel;
    }
// Метод возвращает панель, включающую панели 3 панели: Red, Green, Blue

    private JPanel CreateAllControlPanels() {
        allControlPanels.setLayout(new BorderLayout());
        controlsPanel = CreateControlsPanel();
        allControlPanels.add(controlsPanel, BorderLayout.PAGE_START);
        controlsPanel2 = CreateControlsPanel2();
        allControlPanels.add(controlsPanel2, BorderLayout.CENTER);
        controlsPanel3 = CreateControlsPanel3();
        allControlPanels.add(controlsPanel3, BorderLayout.PAGE_END);

        return allControlPanels;
    }
// Метод обрабатывает события слайдера Red

    private void onSliderChange(ChangeEvent e) {
        int value = slider.getValue();
        colorPanel.setBackground(new Color(value, slider2.getValue(), slider3.getValue()));
    }
// Метод обрабатывает события слайдера Green

    private void onSlider2Change(ChangeEvent e) {
        int value = slider2.getValue();
        colorPanel.setBackground(new Color(slider.getValue(), value, slider3.getValue()));
    }
// Метод обрабатывает события слайдера Blue

    private void onSlider3Change(ChangeEvent e) {
        int value = slider3.getValue();
        colorPanel.setBackground(new Color(slider.getValue(), slider2.getValue(), value));
    }
// Метод обрабатывает события перемещения мыши
// Фактически метод не работает. 
//Я так и не понял почему не происходит обработка перемещений мыши...

    private class MouseEventListener extends MouseAdapter {

        private JLabel label = new JLabel("Label", JLabel.CENTER);

        public void MouseMoved(MouseEvent e) {
            int x = e.getX();
            String text = Integer.toString(x);
            int y = e.getY();
            String text2 = Integer.toString(y);
            label.setText("Координаты: " + text + ", " + text2);
        }

        // Здесь должна быть аннотация @Override, 
        // но среда разработки выдает ошибку... 
        // Я не понял почему...
        protected void onCreate() {
            MouseAdapter listener = new MouseEventListener();
            addMouseMotionListener(listener);
            add(label);
        }
    }
}
