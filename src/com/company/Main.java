package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main { // Данная программа демонстрирует работу приложения для шифрования текста без использования дополнительных классов

    private static JComboBox comboBox;
    private static JFrame Frame;
    private static JButton ConvertButton;
    private static JTextField TextFieldBefore;
    private static JTextField TextFieldAfter;
    private static JPanel Panel;
    private static JLabel LabelBefore;
    private static JLabel LabelAfter;
    private static JTextField TextKey;
    private static JLabel KeyFild;

    public static void main(String[] args) {
        CreateGUI();
        JOptionPane.showMessageDialog(null, "Введите строчку на Русском языке строчными символами, размерность ключей не больше 20", JOptionPane.MESSAGE_PROPERTY, JOptionPane.WARNING_MESSAGE);
    }

    public static void CreateGUI() {

        Frame = new JFrame("Enigma");
        ConvertButton = new JButton("Шифровать");
        Panel = new JPanel();
        TextFieldBefore = new JTextField();
        TextFieldAfter = new JTextField();
        LabelBefore = new JLabel("Текс до шифрования");
        LabelAfter = new JLabel("Результат");
        String[] Ciphers = {"Цезарь", "ROW-2", "Виженер"}; // Массив с названием шифров
        comboBox = new JComboBox(Ciphers);
        TextKey = new JTextField();
        KeyFild = new JLabel("Ключ");

        Frame.setVisible(true); // Отображение фрейма;
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Завершение программы при закрытии окна
        Frame.setLocationRelativeTo(null); // Отображение Фрейма по середине экрана
        Frame.setBackground(Color.DARK_GRAY); // Цвет фона
        Frame.setSize(420, 220); // Размер

        Panel.setLayout(null); // Создание формы без использование Layot Maneger
        Panel.setBackground(Color.LIGHT_GRAY);
        Frame.add(Panel); // Добавление панели на Фрейм

        ConvertButton.setBounds(25, 20, 120, 25); // Расположение и размеры объектов
        Panel.add(ConvertButton);

        TextFieldBefore.setBounds(25, 80, 170, 25);
        Panel.add(TextFieldBefore);

        TextFieldAfter.setBounds(25, 120, 170, 25);
        Panel.add(TextFieldAfter);

        TextKey.setBounds(250, 50, 80, 20);
        Panel.add(TextKey);
        TextKey.setVisible(true);

        LabelAfter.setBounds(250, 120, 170, 25);
        Panel.add(LabelAfter);

        KeyFild.setBounds(340, 50, 60, 20);
        Panel.add(KeyFild);
        KeyFild.setVisible(true);

        LabelBefore.setBounds(250, 80, 170, 25);
        Panel.add(LabelBefore);

        comboBox.setSelectedItem(1); // Выбор первого элемента по умолчанию
        comboBox.setBounds(250, 20, 120, 25);
        Panel.add(comboBox);

        CombobxEvent(); // Вызов метода для обработки выбора элемента Combobox
        ButtonEventAll();  // Вызов метода для обработки нажатия кнопки


    }

    private static String Ceasar(String Value, int shift) { // Кодировка UTF-8
        char[] mas = Value.toCharArray();
        int t; // переменная t для смешения символа, переменная shift является ключом смещения
        for (int i = 0; i < mas.length; i++) {
            if ((((int) mas[i] >= 1072) && ((int) mas[i] <= 1103))) { // Код элемента в кодировке UTF-8, для других не работает)
                if (((int) mas[i] + shift > 1103)) {
                    t = (int) mas[i] + shift - 32;
                } else {
                    t = (int) mas[i] + shift;
                }
                mas[i] = (char) t;
            } else return Value = "ошибка при вводе";
        }
        return Value = String.valueOf(mas);
    }

    private static String Row(String ValueO) { // Простой шифр ROW-2 - сдвиг на 2 позиции
        char[] mas = ValueO.toCharArray();
        int t; // переменная для сдвига номера символа
        for (int i = 0; i < mas.length; i++) {
            if ((((int) mas[i] >= 1072) && ((int) mas[i] <= 1103))) {
                if (((int) mas[i] + 2 > 1103)) {
                    t = (int) mas[i] + 2 - 32;
                } else {
                    t = (int) mas[i] + 2;
                }
                mas[i] = (char) t;
            } else return ValueO = "ошибка при вводе";
        }
        return ValueO = String.valueOf(mas);
    }

    private static String Viner(String Value, String Key) { // Шифр Виженера где используются 2 массива для ключа и самого сообщения соответственно
        char[] MasValue = Value.toCharArray();
        char[] MasKey = Key.toCharArray();
        if (MasKey.length == MasValue.length) {
            for (int i = 0; i < MasValue.length; i++) {
                if ((((int) MasKey[i] >= 1072) && ((int) MasKey[i] <= 1103)) && ((int) MasValue[i] >= 1072) && ((int) MasValue[i] <= 1103)) {
                    int KeyShift = (int) MasKey[i] - 1071; // Ключ нумеруется от 1 до 32 соответственно
                    int ValueShift = (int) MasValue[i] + KeyShift;
                    MasValue[i] = (char) ValueShift;
                } else return Value = "ошибка при вводе";
            }
        } else return Value = "Несоответствие размерности шифра";

        return Value = String.valueOf(MasValue);
    }

    public static void CombobxEvent() { // Обработка выбора элемента Combobox
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (comboBox.getSelectedItem().toString()) {
                    case "Цезарь":
                        TextKey.setVisible(true); // отображение поля для ключа
                        KeyFild.setVisible(true);
                        TextKey.setText(null); // очищаяет поля
                        TextFieldBefore.setText(null);
                        TextFieldAfter.setText(null);
                        break;
                    case "ROW-2": {
                        TextKey.setVisible(false);
                        KeyFild.setVisible(false);
                        TextFieldBefore.setText(null);
                        TextFieldAfter.setText(null);
                        break;
                    }
                    case "Виженер": {
                        TextKey.setVisible(true);
                        KeyFild.setVisible(true);
                        TextKey.setText(null);
                        TextFieldBefore.setText(null);
                        TextFieldAfter.setText(null);
                        break;
                    }
                }
            }
        };
        comboBox.addActionListener(listener); // Добавление события обработки
    }

    public static void ButtonEventAll() {
        ActionListener CeasarEvent = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBox.getSelectedItem().toString().equals("Цезарь")) {
                    try {
                        TextFieldAfter.setText(Ceasar(TextFieldBefore.getText(), Integer.parseInt(TextKey.getText()))); // Взятие текста из полей и шифрование данных
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Ошибка ввода");
                    }

                } else {
                    if (comboBox.getSelectedItem().toString().equals("ROW-2")) {
                        try {
                            TextFieldAfter.setText(Row(TextFieldBefore.getText()));
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Ошибка ввода");
                        }
                    } else {
                        try {
                            TextFieldAfter.setText(Viner(TextFieldBefore.getText(), TextKey.getText()));
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Ошибка ввода");
                        }
                    }
                }
            }
        };
        ConvertButton.addActionListener(CeasarEvent);
    }
}

// Данная программа создавалась с целью изучение Swing библиотеки и имеет некоторые ошибки, однако основные источники исключения были обработаны
// Алгоритмы шифрования реализованы самым легким и путем для их демонстрирования и не более того...