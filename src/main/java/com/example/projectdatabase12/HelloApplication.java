package com.example.projectdatabase12;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;

//import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.DashedBorder;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.property.BaseDirection;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.itextpdf.text.pdf.languages.ArabicLigaturizer;
import com.itextpdf.text.pdf.languages.LanguageProcessor;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.converter.BooleanStringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class HelloApplication extends Application {
    Scene memberPage, loginPage, signupPage, CoachPage, nutritionistPage, WorkOutPlanPage, FoodPlanPage, paymentPage, bioPage, ItemsPage, SalesPage, MainPage;
    ObservableList<Member> memberList = FXCollections.observableArrayList();
    ObservableList<Coach> CoachList = FXCollections.observableArrayList();
    ObservableList<Nutritionist> nutritionistList = FXCollections.observableArrayList();
    ObservableList<WorkOutPlan> WorkOutPlanList = FXCollections.observableArrayList();
    ObservableList<FoodPlan> FoodPlanList = FXCollections.observableArrayList();
    ObservableList<Payment> paymentList = FXCollections.observableArrayList();
    ObservableList<Bio> bioList = FXCollections.observableArrayList();
    ObservableList<Items> ItemsData = FXCollections.observableArrayList();
    ObservableList<Items> SalesData = FXCollections.observableArrayList();
    ObservableList<LogIn> LoginList = FXCollections.observableArrayList();

    DataBaseConnection db = new DataBaseConnection();
    File f = null;
    String path = null;

    File f1 = null;
    String path1 = null;
    static String userLogin = "";
    static String rankLogin = "";

    private void readMemberData() throws SQLException, ClassNotFoundException {

        try {
            Connection con = db.getConnection().connectDB();
            String sql = "SELECT * FROM Members";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                memberList.add(new Member(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getDouble(5), rs.getString(6), rs.getString(7), rs.getBoolean(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13)));

            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ReadPayment() throws IOException, SQLException, ClassNotFoundException {
        Connection con = db.getConnection().connectDB();
        try {
            String sql = "SELECT * FROM payment";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                paymentList.add(new Payment(rs.getInt(1), rs.getDouble(2), rs.getString(3), rs.getString(4)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void readCoachData() throws SQLException, ClassNotFoundException {

        try {
            Connection con = db.getConnection().connectDB();
            String sql = "SELECT * FROM Coaches";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                CoachList.add(new Coach(rs.getString(1), rs.getString(2), rs.getString(3)));

            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readNutritionistData() {
        try {
            Connection con = db.getConnection().connectDB();
            String sql = "SELECT * FROM Nutritionists";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                nutritionistList.add(new Nutritionist(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));

            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readWorkOutPlanData() {
        try {
            Connection con = db.getConnection().connectDB();
            String sql = "SELECT * FROM WorkOutPlan";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                String nm = rs.getString(6);

                ImageView ii = new ImageView(nm);

                Image img = ii.getImage();

                WorkOutPlanList.add(new WorkOutPlan(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), new ImageView(img)));

            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readFoodPlanData() {
        try {
            Connection con = db.getConnection().connectDB();
            String sql = "SELECT * FROM FoodPlan";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                String nm = rs.getString(6);

                ImageView ii = new ImageView(nm);

                Image img = ii.getImage();

                FoodPlanList.add(new FoodPlan(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), new ImageView(img)));

            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readBioData() {
        try {
            Connection con = db.getConnection().connectDB();
            String sql = "SELECT * FROM Bio";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                bioList.add(new Bio(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));

            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readItems() throws IOException, SQLException, ClassNotFoundException {
        Connection con = db.getConnection().connectDB();
        try {
            String sql = "SELECT * FROM Items";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ItemsData.add(new Items(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getInt(4), rs.getDouble(5), rs.getDouble(6), rs.getDouble(7)));

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ArrayList<String> getCoaches() {
        Connection conData;
        Statement stmtCoach;
        ResultSet rsCoach;
        ArrayList<String> coaches = new ArrayList<String>();
        coaches.add(null);
        try {
            conData = db.getConnection().connectDB();
            String sql0 = "SELECT C.CID, C.coachName, B.workingTimes, B.WorkingDays FROM coaches C, Bio B WHERE B.EID = C.CID";
            stmtCoach = conData.createStatement();
            rsCoach = stmtCoach.executeQuery(sql0);

            while (rsCoach.next())

                coaches.add(rsCoach.getString(1) + "-" + rsCoach.getString(2) + "-" + rsCoach.getString(3) + "-" + rsCoach.getString(4));
        } catch (ClassNotFoundException | SQLException e3) {
            displayErrorMassage("Coudn't get coaches data.");
            e3.printStackTrace();
        }
        return coaches;

    }

    public ArrayList<String> getNur() {
        Connection conData;
        Statement stmtNutr;
        ResultSet rsNutr;
        ArrayList<String> nutritionists = new ArrayList<String>();
        nutritionists.add(null);

        try {
            conData = db.getConnection().connectDB();
            String sql0 = "SELECT N.NID, N.nutritionistName, B.workingDays , B.WorkingTimes FROM Nutritionists N, Bio B WHERE N.NID = B.EID ";
            stmtNutr = conData.createStatement();
            rsNutr = stmtNutr.executeQuery(sql0);

            while (rsNutr.next()) {

                nutritionists.add(rsNutr.getString(1) + "-" + rsNutr.getString(2) + "-" + rsNutr.getString(3) + "-" + rsNutr.getString(4));

            }
            conData.close();
        } catch (ClassNotFoundException | SQLException e3) {
            displayErrorMassage("Coudn't get Nutritionists data.");
            e3.printStackTrace();
        }

        return nutritionists;
    }

    static Cell getHeaderTextCell(String textValue) {

        return new Cell().add(textValue).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT);
    }

    static Cell getHeaderTextCellValue(String textValue) {

        return new Cell().add(textValue).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
    }

    static Cell getBillingandShippingCell(String textValue) {

        return new Cell().add(textValue).setFontSize(12f).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
    }

    static Cell getCell10fLeft(String textValue, Boolean isBold) {
        Cell myCell = new Cell().add(textValue).setFontSize(10f).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
        return isBold ? myCell.setBold() : myCell;

    }

    public void readLoginData() throws SQLException, ClassNotFoundException {

        try {
            Connection con = db.getConnection().connectDB();
            String sql = "SELECT * FROM Login";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                LoginList.add(new LogIn(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));

            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        try {

            readLoginData();
            readMemberData();
            readCoachData();
            readNutritionistData();
            readFoodPlanData();
            readWorkOutPlanData();
            ReadPayment();
            readBioData();
            readItems();
        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


//=============================================MemberPage=====================================================

        Image memberBack = new Image("DB.jpg");
        ImageView memberBackImage = new ImageView(memberBack);
        memberBackImage.setFitHeight(700);
        memberBackImage.setFitWidth(900);

        TableView<Member> tableMember = new TableView<>();
        tableMember.setEditable(true);

        TableColumn<Member, String> Id = new TableColumn<>("ID");
        Id.setPrefWidth(60);
        Id.setResizable(false);
        Id.setCellValueFactory(new PropertyValueFactory<Member, String>("GID"));

        TableColumn<Member, String> Name = new TableColumn<>("Name");
        Name.setPrefWidth(100);
        Name.setResizable(false);
        Name.setCellValueFactory(new PropertyValueFactory<Member, String>("name"));
        Name.setCellFactory(TextFieldTableCell.forTableColumn());
        Name.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Member, String>>() {

            @Override
            public void handle(CellEditEvent<Member, String> arg0) {

                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setName(arg0.getNewValue());
                String id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getGID();
                String name = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getName();

                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE Members set Name ='" + name + "'  WHERE GID='" + id + "'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }
            }
        });

        TableColumn<Member, Integer> age = new TableColumn<>("Age");
        age.setPrefWidth(60);
        age.setResizable(false);
        age.setCellValueFactory(new PropertyValueFactory<Member, Integer>("age"));
        age.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        age.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Member, Integer>>() {

            @Override
            public void handle(CellEditEvent<Member, Integer> arg0) {

                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setAge(arg0.getNewValue());
                String id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getGID();
                int Age = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getAge();

                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE Members set age ='" + Age + "'  WHERE GID='" + id + "'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }
            }
        });

        TableColumn<Member, Double> wht = new TableColumn<>("Weight");
        wht.setPrefWidth(60);
        wht.setResizable(false);
        wht.setCellValueFactory(new PropertyValueFactory<Member, Double>("weight"));
        wht.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        wht.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Member, Double>>() {

            @Override
            public void handle(CellEditEvent<Member, Double> arg0) {

                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setWeight(arg0.getNewValue());
                String id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getGID();
                double Weight = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getWeight();

                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE Members SET weight ='" + Weight + "' WHERE GID='" + id + "'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }
            }
        });

        TableColumn<Member, Double> het = new TableColumn<>("Height");
        het.setPrefWidth(60);
        het.setResizable(false);
        het.setCellValueFactory(new PropertyValueFactory<Member, Double>("height"));
        het.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        het.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Member, Double>>() {

            @Override
            public void handle(CellEditEvent<Member, Double> arg0) {
                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setHeight(arg0.getNewValue());
                String id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getGID();
                double Height = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getHeight();

                try {
                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE Members SET height = " + Height + " WHERE GID = '" + id + "'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }
            }
        });

        TableColumn<Member, String> address = new TableColumn<>("Address");
        address.setPrefWidth(80);
        address.setResizable(false);
        address.setCellValueFactory(new PropertyValueFactory<Member, String>("address"));
        address.setCellFactory(TextFieldTableCell.forTableColumn());
        address.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Member, String>>() {

            @Override
            public void handle(CellEditEvent<Member, String> arg0) {
                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setAddress(arg0.getNewValue());
                String id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getGID();
                String name = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getAddress();

                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE Members SET addrees ='" + name + "' WHERE GID='" + id + "'"; // '"++"'
                    String sql2 = "UPDATE Members SET addrees='"+name+"' where GID='"+id+"'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }
            }
        });

        TableColumn<Member, String> phone = new TableColumn<>("Phone Number");
        phone.setPrefWidth(90);
        phone.setResizable(false);
        phone.setCellValueFactory(new PropertyValueFactory<Member, String>("phoneNum"));
        phone.setCellFactory(TextFieldTableCell.forTableColumn());
        phone.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Member, String>>() {

            @Override
            public void handle(CellEditEvent<Member, String> arg0) {

                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setPhoneNum(arg0.getNewValue());
                String id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getGID();
                String name = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getPhoneNum();

                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE Members SET phoneNumber ='" + name + "' WHERE gID='" + id + "'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }
            }
        });

        TableColumn<Member, Boolean> locker = new TableColumn<>("Locker");
        locker.setPrefWidth(60);
        locker.setResizable(false);
        locker.setCellValueFactory(new PropertyValueFactory<Member, Boolean>("lockerOption"));

        ObservableList<Boolean> options2 = FXCollections.observableArrayList(true, false);
        locker.setCellFactory(ChoiceBoxTableCell.forTableColumn(options2));
        locker.setOnEditCommit(event -> {
            Member workOutPlan = event.getRowValue();
            workOutPlan.setLockerOption(event.getNewValue());
            boolean name = workOutPlan.isLockerOption();
            String id = workOutPlan.getGID();
            byte n = 0;

            try {
                if (name == true) {
                    n = 1;
                }

                Connection con = db.getConnection().connectDB();
                String sql = "UPDATE Members set lockerOp ='" + n + "'  WHERE GID='" + id + "'";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(sql);
                con.close();

            } catch (Exception e2) {
                e2.getMessage();
            }
        });

        TableColumn<Member, String> date = new TableColumn<>("End Date");
        date.setPrefWidth(80);
        date.setResizable(false);
        date.setCellValueFactory(new PropertyValueFactory<Member, String>("membershipEndDate"));
        date.setCellFactory(TextFieldTableCell.forTableColumn());

        date.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Member, String>>() {

            @Override
            public void handle(CellEditEvent<Member, String> arg0) {

                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setMembershipEndDate(arg0.getNewValue());
                String id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getGID();
                String name = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getMembershipEndDate();

                try {
                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE Members SET EndDate ='" + name + "' WHERE gID ='" + id + "'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }
            }
        });

        TableColumn<Member, String> cId = new TableColumn<>("CID");
        cId.setPrefWidth(50);
        cId.setResizable(false);
        cId.setCellValueFactory(new PropertyValueFactory<Member, String>("CID"));

        ArrayList<String> rr = getCoaches();
        ObservableList<String> coachchose = FXCollections.observableArrayList(rr);
        cId.setCellFactory(ChoiceBoxTableCell.forTableColumn(coachchose));
        cId.setOnEditCommit(event -> {
            if (event.getNewValue() == null) {

                Member workOutPlan = event.getRowValue();
                workOutPlan.setCID(null);

                String id = workOutPlan.getGID();
                String cid = null;

                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE Members set CID = null  WHERE GID='" + id + "'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                return;

            } else {
                Member workOutPlan = event.getRowValue();
                workOutPlan.setCID(event.getNewValue().split("-")[0]);

                String id = workOutPlan.getGID();
                String cid = workOutPlan.getCID();

                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE Members set CID ='" + cid + "'  WHERE GID='" + id + "'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }


        });

        TableColumn<Member, String> nIds = new TableColumn<>("NID");
        nIds.setPrefWidth(50);
        nIds.setResizable(false);
        nIds.setCellValueFactory(new PropertyValueFactory<Member, String>("NID"));
        ArrayList<String> rr1 = getNur();
        ObservableList<String> nutrt = FXCollections.observableArrayList(rr1);
        nIds.setCellFactory(ChoiceBoxTableCell.forTableColumn(nutrt));
        nIds.setOnEditCommit(event -> {
            if (event.getNewValue() == null) {

                Member workOutPlan = event.getRowValue();
                workOutPlan.setNID(null);

                String id = workOutPlan.getGID();
                String cid = null;

                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE Members set NID = null  WHERE GID='" + id + "'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                return;

            } else {
                Member workOutPlan = event.getRowValue();
                workOutPlan.setNID(event.getNewValue().split("-")[0]);

                String id = workOutPlan.getGID();
                String cid = workOutPlan.getNID();

                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE Members set NID ='" + cid + "'  WHERE GID='" + id + "'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }
            }


        });

        TableColumn<Member, String> Pid = new TableColumn<>("PID");
        Pid.setPrefWidth(50);
        Pid.setResizable(false);
        Pid.setCellValueFactory(new PropertyValueFactory<Member, String>("PID"));
        Pid.setCellFactory(TextFieldTableCell.forTableColumn());
        Pid.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Member, String>>() {

            @Override
            public void handle(CellEditEvent<Member, String> arg0) {
                String NEW = arg0.getNewValue();
                int fg = 0;
                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "SELECT P.PID FROM FoodPlan P WHERE P.PID = " + NEW + " ";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                    if (!rs.next()) {
                        fg = 1;
                        Alert alert = new Alert(Alert.AlertType.NONE, "PID is not valid", ButtonType.OK);
                        if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                        }
                        memberList.clear();
                        readMemberData();
                        tableMember.refresh();
                    }

                    con.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (fg == 1) {
                    return;
                }

                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setPID(arg0.getNewValue());
                String id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getGID();
                String name = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getPID();

                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE Members SET PID ='" + name + "' WHERE gID='" + id + "'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }
            }
        });


        TableColumn<Member, String> Wid = new TableColumn<>("WID");
        Wid.setPrefWidth(50);
        Wid.setResizable(false);
        Wid.setCellValueFactory(new PropertyValueFactory<Member, String>("WID"));
        Wid.setCellFactory(TextFieldTableCell.forTableColumn());
        Wid.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Member, String>>() {

            @Override
            public void handle(CellEditEvent<Member, String> arg0) {

                String NEW = arg0.getNewValue();
                int fg = 0;
                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "SELECT P.WID FROM WorkOutPlan P WHERE P.WID = " + NEW + " ";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                    if (!rs.next()) {
                        fg = 1;
                        Alert alert = new Alert(Alert.AlertType.NONE, "WID is not valid", ButtonType.OK);
                        if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                        }
                        memberList.clear();
                        readMemberData();
                        tableMember.refresh();
                    }

                    con.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (fg == 1) {
                    return;
                }


                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setWID(arg0.getNewValue());
                String id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getGID();
                String name = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getWID();

                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE Members SET WID ='" + name + "' WHERE gID='" + id + "'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }
            }
        });

        tableMember.getColumns().addAll(Id, Name, age, wht, het, address, phone, locker, date, cId, nIds, Pid, Wid);
        tableMember.setPrefSize(850, 436);
        tableMember.setLayoutX(25);
        tableMember.setLayoutY(110);

        tableMember.setItems(memberList);

        Label MemberLabel = new Label("Member");
        MemberLabel.setLayoutX(367);
        MemberLabel.setLayoutY(26);
        MemberLabel.setFont(Font.font("Robot", 36));
        MemberLabel.setTextFill(Color.web("#ffffff"));
        MemberLabel.setStyle("-fx-font-size: 30pt; -fx-text-fill: #ffffff;");

        Button addMember = new Button("Add", new ImageView("add.png"));
        addMember.setLayoutX(307);
        addMember.setLayoutY(596);
        addMember.setPrefWidth(70);
        addMember.setPrefHeight(57);
        addMember.setTextFill(Color.BLACK);
        addMember.setContentDisplay(ContentDisplay.TOP);
        addMember.setOnAction(e -> {

            GridPane gr = new GridPane();

            Stage addMemberStage = new Stage();
            addMemberStage.setResizable(false);
            addMemberStage.setTitle("Add Member Window");

            Label gIDLabel = new Label("Gym ID: ");
            TextField gIDInput = new TextField();

            Label nameLabel = new Label("Name: ");
            TextField nameInput = new TextField();

            Label ageLabel = new Label("Age: ");
            TextField ageInput = new TextField();

            Label weightLabel = new Label("Weight: ");
            TextField weightInput = new TextField();

            Label heightLabel = new Label("Height: ");
            TextField heightInput = new TextField();

            Label addressLabel = new Label("Address: ");
            TextField addressInput = new TextField();

            Label phoneNumLabel = new Label("Phone Number: ");
            TextField phoneNumInput = new TextField();

            Label lockerLabel = new Label("Locker Option: ");
            ChoiceBox<Boolean> lockerInput = new ChoiceBox<Boolean>(FXCollections.observableArrayList(true, false));

            Label membershipEndDateLabel = new Label("Membership end date (DD-MM-YY): ");
            DatePicker membershipEndDateInput = new DatePicker();

            Label PidLabel = new Label("PID : ");
            TextField PidInput = new TextField();

            Label WIDLabel = new Label("WID : ");
            TextField WIDInput = new TextField();

            ArrayList<String> nutritionists = new ArrayList<String>();
            ArrayList<String> coaches = new ArrayList<String>();


            gr.add(gIDLabel, 0, 0);
            gr.add(gIDInput, 1, 0);
            gr.add(nameLabel, 0, 1);
            gr.add(nameInput, 1, 1);
            gr.add(ageLabel, 0, 2);
            gr.add(ageInput, 1, 2);
            gr.add(weightLabel, 0, 3);
            gr.add(weightInput, 1, 3);
            gr.add(heightLabel, 0, 4);
            gr.add(heightInput, 1, 4);
            gr.add(addressLabel, 0, 5);
            gr.add(addressInput, 1, 5);
            gr.add(phoneNumLabel, 0, 6);
            gr.add(phoneNumInput, 1, 6);
            gr.add(lockerLabel, 0, 7);
            gr.add(lockerInput, 1, 7);
            gr.add(membershipEndDateLabel, 0, 8);
            gr.add(membershipEndDateInput, 1, 8);

            gr.add(PidLabel, 0, 11);
            gr.add(PidInput, 1, 11);
            gr.add(WIDLabel, 0, 12);
            gr.add(WIDInput, 1, 12);

            Connection conData;
            Statement stmtCoach;
            ResultSet rsCoach;

            coaches.add(null);

            try {
                conData = db.getConnection().connectDB();
                String sql0 = "SELECT C.CID, C.coachName, B.workingTimes, B.WorkingDays FROM coaches C, Bio B WHERE B.EID = C.CID";
                stmtCoach = conData.createStatement();
                rsCoach = stmtCoach.executeQuery(sql0);

                while (rsCoach.next()) {
                    coaches.add(rsCoach.getString(1) + "-" + rsCoach.getString(2) + "-" + rsCoach.getString(3) + "-" + rsCoach.getString(4));
                }
                conData.close();
                // Collections.sort(coaches);
            } catch (ClassNotFoundException | SQLException | NullPointerException e3) {
                displayErrorMassage("Coudn't get coaches data.");
                e3.printStackTrace();
            }

            Statement stmtNutr;
            ResultSet rsNutr;

            nutritionists.add(null);

            try {
                conData = db.getConnection().connectDB();
                String sql0 = "SELECT N.NID, N.nutritionistName, B.workingDays , B.WorkingTimes FROM Nutritionists N, Bio B WHERE N.NID = B.EID ";
                stmtNutr = conData.createStatement();
                rsNutr = stmtNutr.executeQuery(sql0);

                while (rsNutr.next()) {

                    nutritionists.add(rsNutr.getString(1) + "-" + rsNutr.getString(2) + "-" + rsNutr.getString(3) + "-" + rsNutr.getString(4));

                }
                conData.close();
            } catch (ClassNotFoundException | SQLException e3) {
                displayErrorMassage("Coudn't get Nutritionists data.");
                e3.printStackTrace();
            }

//            VBox labels = new VBox(10);
//            labels.getChildren().addAll(gIDLabel, nameLabel, ageLabel, weightLabel, heightLabel, addressLabel, phoneNumLabel, lockerLabel, membershipEndDateLabel, coachLabel, NutrLabel , PidLabel , WIDLabel);
//
//            VBox inputs = new VBox(1);
//            inputs.getChildren().addAll(gIDInput, nameInput, ageInput, weightInput, heightInput, addressInput, phoneNumInput, lockerInput, membershipEndDateInput, coachInput, nutrInput, PidInput, WIDInput);
//
//            HBox boxes = new HBox();
//            boxes.getChildren().addAll(labels, inputs);

            Label coachLabel = new Label("Coach: ");
            ChoiceBox<String> coachInput = new ChoiceBox<String>(FXCollections.observableArrayList(coaches));

            Label NutrLabel = new Label("Nutritionist: ");
            ChoiceBox<String> nutrInput = new ChoiceBox<String>(FXCollections.observableArrayList(nutritionists));
            gr.add(coachLabel, 0, 9);
            gr.add(coachInput, 1, 9);
            gr.add(NutrLabel, 0, 10);
            gr.add(nutrInput, 1, 10);

            Button addMemberButton = new Button("", new ImageView("add.png"));

            addMemberButton.setOnAction(e1 -> {
                String co = null;
                String nu = null;
                String pid = PidInput.getText().trim();
                String wid = WIDInput.getText().trim();

                nu = nutrInput.getValue() == null ? "NULL" : nutrInput.getValue().toString().split("-")[0];
                co = coachInput.getValue() == null ? "NULL" : coachInput.getValue().toString().split("-")[0];

                if (pid.isEmpty()) {
                    pid = "NULL";
                }
                if (wid.isEmpty()) {
                    wid = "NULL";
                }

                try {
                    Connection con = db.getConnection().connectDB();
                    String sql0 = "Insert Into Members (GID,Name,age,weight,height,addrees,phoneNumber,lockerOp,EndDate,CID,NID,PID,WID) values(" + "'" + gIDInput.getText() + "'" + ", " + "'" + nameInput.getText() + "'" + ", " + ageInput.getText() + ", " +
                            weightInput.getText() + ", " + heightInput.getText() + ", " + "'" + addressInput.getText() + "'" + ", " + "'" + phoneNumInput.getText()
                            + "'" + ", " + lockerInput.getValue() + ", " + "'" + membershipEndDateInput.getValue().toString() + "'" + "," + (co.equals("NULL") ? "DEFAULT" : "'" + co + "'") + ","
                            + (nu.equals("NULL") ? "DEFAULT" : "'" + nu + "'") + "," + pid + "," + wid + ")";

                    Statement stmt = con.createStatement();
                    stmt.execute(sql0);

                    con.close();

                    memberList.add(new Member(gIDInput.getText(), nameInput.getText(), Integer.parseInt(ageInput.getText()), Double.parseDouble(weightInput.getText()), Double.parseDouble(heightInput.getText()), addressInput.getText(), phoneNumInput.getText(), lockerInput.getValue(), membershipEndDateInput.getValue().toString(), co, nu, pid, wid));

                } catch (SQLException e2) {
                    displayErrorMassage("Primary key already exists!");
                    e2.getMessage();
                } catch (ClassNotFoundException e2) {
                    Alert alert = new Alert(Alert.AlertType.NONE, "Error sending data to database!!!", ButtonType.OK);
                    if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                    }
                    e2.getMessage();
                }

            });

            gr.add(addMemberButton, 1, 13);

            gr.setMargin(gIDLabel, new Insets(10, 10, 10, 10));
            gr.setMargin(gIDInput, new Insets(10, 10, 10, 10));
            gr.setMargin(nameLabel, new Insets(10, 10, 10, 10));
            gr.setMargin(nameInput, new Insets(10, 10, 10, 10));
            gr.setMargin(ageLabel, new Insets(10, 10, 10, 10));
            gr.setMargin(ageInput, new Insets(10, 10, 10, 10));
            gr.setMargin(weightLabel, new Insets(10, 10, 10, 10));
            gr.setMargin(weightInput, new Insets(10, 10, 10, 10));
            gr.setMargin(heightLabel, new Insets(10, 10, 10, 10));
            gr.setMargin(heightInput, new Insets(10, 10, 10, 10));
            gr.setMargin(addressLabel, new Insets(10, 10, 10, 10));
            gr.setMargin(addressInput, new Insets(10, 10, 10, 10));
            gr.setMargin(phoneNumLabel, new Insets(10, 10, 10, 10));
            gr.setMargin(phoneNumInput, new Insets(10, 10, 10, 10));
            gr.setMargin(lockerLabel, new Insets(10, 10, 10, 10));
            gr.setMargin(lockerInput, new Insets(10, 10, 10, 10));
            gr.setMargin(membershipEndDateLabel, new Insets(10, 10, 10, 10));
            gr.setMargin(membershipEndDateInput, new Insets(10, 10, 10, 10));
            gr.setMargin(coachLabel, new Insets(10, 10, 10, 10));
            gr.setMargin(coachInput, new Insets(10, 10, 10, 10));
            gr.setMargin(NutrLabel, new Insets(10, 10, 10, 10));
            gr.setMargin(nutrInput, new Insets(10, 10, 10, 10));
            gr.setMargin(PidInput, new Insets(10, 10, 10, 10));
            gr.setMargin(PidLabel, new Insets(10, 10, 10, 10));
            gr.setMargin(WIDLabel, new Insets(10, 10, 10, 10));
            gr.setMargin(WIDInput, new Insets(10, 10, 10, 10));

            gr.setAlignment(Pos.CENTER);
            Scene scene = new Scene(gr, 500, 700);
            scene.getStylesheets().add(getClass().getResource("stage2.css").toExternalForm());

            addMemberStage.setScene(scene);

            addMemberStage.show();
        });

        Button backMember = new Button("Back", new ImageView("Back.png"));
        backMember.setLayoutX(97);
        backMember.setLayoutY(596);
        backMember.setPrefWidth(70);
        backMember.setPrefHeight(57);

        backMember.setTextFill(Color.BLACK);
        backMember.setContentDisplay(ContentDisplay.TOP);
        backMember.setOnAction(e -> {
            primaryStage.setScene(MainPage);
            primaryStage.setResizable(false);
        });

        Button deleteMember = new Button("Delete", new ImageView("can.png"));
        deleteMember.setLayoutX(758);
        deleteMember.setLayoutY(596);
        deleteMember.setPrefWidth(70);
        deleteMember.setPrefHeight(57);

        deleteMember.setTextFill(Color.BLACK);
        // deleteMember.setFont(javafx.scene.text.Font.font("Oranienbaum", 18));
        deleteMember.setContentDisplay(ContentDisplay.TOP);
        deleteMember.setOnAction(e -> {
            if (tableMember.getSelectionModel().getSelectedItem() == null) {
                Alert alert = new Alert(Alert.AlertType.NONE, "You Must Select a Row", ButtonType.OK);
                if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                }
                return;
            }
            Member selectedItem = tableMember.getSelectionModel().getSelectedItem();
            String id = selectedItem.getGID();

            try {

                Connection con = db.getConnection().connectDB();
                String sql = "select * from Payment WHERE gID='" + id + "'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    Alert alert = new Alert(Alert.AlertType.NONE, "The Member still hasn't paid the bills ", ButtonType.OK);
                    if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                    }
                    return;
                }
                con.close();


            } catch (Exception e2) {
                e2.printStackTrace();
            }

            tableMember.getItems().remove(selectedItem);

            try {

                Connection con = db.getConnection().connectDB();
                String sql = "Delete from Members WHERE gID='" + id + "'";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(sql);
                con.close();

            } catch (Exception e2) {
                e2.printStackTrace();
            }
        });

        Button ShowPay = new Button("Show Payments", new ImageView("eye.png"));
        ShowPay.setLayoutX(529);
        ShowPay.setLayoutY(596);
        ShowPay.setPrefWidth(70);
        ShowPay.setPrefHeight(57);

        ShowPay.setTextFill(Color.BLACK);
        ShowPay.setContentDisplay(ContentDisplay.TOP);
        ShowPay.setOnAction(e -> {
            if (tableMember.getSelectionModel().getSelectedItem() == null) {
                Alert alert = new Alert(Alert.AlertType.NONE, "You Must Select a Row", ButtonType.OK);
                if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                }
                return;
            }
            try {
                Stage stage = new Stage();

                Connection con = db.getConnection().connectDB();

                String sql1 = "SELECT P.amount, P.dateSale  from Payment P where P.GID  = '" + tableMember.getSelectionModel().getSelectedItem().getGID() + "'";
                Statement stmt1 = con.createStatement();
                ResultSet rs = stmt1.executeQuery(sql1);

                double totalSum = 0;
                TextArea textArea = new TextArea();

                while (rs.next()) {
                    textArea.appendText("Amount: " + rs.getString(1) + "  Date: " + rs.getString(2) + "\n");
                    totalSum += rs.getDouble(1);
                }
                textArea.appendText("Total: " + totalSum + "\n");

                VBox labels = new VBox(5);
                labels.getChildren().addAll(textArea);

                Scene scene = new Scene(labels);
                scene.getStylesheets().add(getClass().getResource("stage2.css").toExternalForm());
                stage.setScene(scene);
                stage.show();

                con.close();

            } catch (Exception e1) {
                e1.printStackTrace();
            }

        });

        Pane memberPane = new Pane();
        memberPane.getChildren().addAll(memberBackImage, tableMember, MemberLabel, addMember, deleteMember, backMember, ShowPay);
        memberPage = new Scene(memberPane, 900, 700);
        memberPage.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

        // ============================== signupPage
        // ======================================================
        Image Regpageimage = new Image("signup.jpg");
        ImageView RegBackImage = new ImageView(Regpageimage);
        RegBackImage.setFitHeight(563);
        RegBackImage.setFitWidth(900);

        TextField userName = new TextField();
        userName.setPrefSize(175, 28);
        userName.setLayoutX(563);
        userName.setLayoutY(198);
        userName.setPromptText("Enter a UserName");

        PasswordField password = new PasswordField();
        password.setPrefSize(175, 28);
        password.setLayoutX(563);
        password.setLayoutY(255);
        password.setPromptText("Enter a Password");

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.setLayoutX(563);
        choiceBox.setLayoutY(310);
        choiceBox.setPrefSize(175, 28);

        choiceBox.setStyle("-fx-background-color: transparent");
        choiceBox.setValue("Member");
        choiceBox.getItems().addAll("Member", "Coach", "Nutritionist");

        Hyperlink backLoginLabel = new Hyperlink("Back to Login !");
        backLoginLabel.setLayoutX(594);
        backLoginLabel.setLayoutY(418);
        backLoginLabel.setFont(javafx.scene.text.Font.font("Barlow Condensed", 12));
        backLoginLabel.setTextFill(Color.WHITE);
        backLoginLabel.setOnAction(e -> {
            userName.clear();
            password.clear();
            choiceBox.setValue("Member");
            primaryStage.setScene(loginPage);
        });

        Button Register = new Button("Sign Up", new ImageView("key.png"));
        Register.setPrefSize(162, 34);
        Register.setLayoutX(556);
        Register.setLayoutY(371);
        Register.setContentDisplay(ContentDisplay.LEFT);
        Register.setOnAction(e -> {

            int flage0M = 0;
            int flage1E = 0;

            int flagmember = 0;
            int emloyeeflag = 0;
            String Eids = null;
            String Mids = null;
            if (choiceBox.getValue().equals("Member")) {
                TextInputDialog dialog = new TextInputDialog("Enter Your ID");
                dialog.setTitle("Member ID");
                dialog.getDialogPane().setContentText("Please enter your ID : ");
                Optional<String> result = dialog.showAndWait();
                TextField ID = dialog.getEditor();
                Mids = ID.getText();

                try {
                    Connection con = db.getConnection().connectDB();
                    String sql = "SELECT M.GID FROM login M WHERE M.GID = '" + ID.getText() + "'";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                    if (rs.next()) {
                        flage0M = 1;
                    }
                    con.close();

                } catch (Exception e1) {
                    e1.getMessage();
                }

                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "SELECT M.GID FROM Members M WHERE M.GID = '" + ID.getText() + "'";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                    if (!rs.next()) {
                        flagmember = 1;
                    }
                    con.close();
                } catch (Exception b) {
                    b.getMessage();
                }

            } else {
                TextInputDialog dialog = new TextInputDialog("Enter Your ID");
                dialog.setTitle("Employee ID");
                dialog.getDialogPane().setContentText("Please enter your ID : ");
                Optional<String> result = dialog.showAndWait();
                TextField ID = dialog.getEditor();
                Eids = ID.getText();
                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "SELECT E.EID FROM login E WHERE E.EID = '" + ID.getText() + "'";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                    if (rs.next()) {
                        flage1E = 1;
                    }
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }

                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "SELECT E.EID FROM Employees E WHERE E.EID = '" + ID.getText() + "'";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                    if (!rs.next()) {
                        emloyeeflag = 1;
                    }
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }

            }

            if (flage0M == 1) {
                Alert alert = new Alert(Alert.AlertType.NONE, " Member ID Already Register ", ButtonType.OK);
                if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                }
                return;
            }

            if (flage1E == 1) {
                Alert alert = new Alert(Alert.AlertType.NONE, "Employee ID Already Registered  ", ButtonType.OK);
                if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                }
                return;
            }

            if (flagmember == 1) {
                Alert alert = new Alert(Alert.AlertType.NONE, "InValid Member ID please try Again  ", ButtonType.OK);
                if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                }
                return;
            }

            if (emloyeeflag == 1) {
                Alert alert = new Alert(Alert.AlertType.NONE, "InValid Employee ID please try Again  ", ButtonType.OK);
                if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                }
                return;
            }

// (co.equals("NULL") ? "DEFAULT" : "'" + co + "'")
            try {
                Connection con = db.getConnection().connectDB();
                String sql = "insert into login(username,Password,AdminType,GID,EID) values(" + "'" + userName.getText() + "', " + "'" + password.getText() + "', " + "'" + choiceBox.getValue() + "', " + (Mids == null ? "DEFAULT" : "'" + Mids + "'") + ", " + (Eids == null ? "DEFAULT" : "'" + Eids + "'") + ")";
                Statement stmt = con.createStatement();
                stmt.execute(sql);
                con.close();
                LoginList.add(new LogIn(userName.getText(), password.getText(), choiceBox.getValue()));

            } catch (Exception m) {
                m.printStackTrace();
            }

            Alert alert = new Alert(Alert.AlertType.NONE, "You have been Registered Successfully ", ButtonType.OK);
            if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
            }

            userName.clear();
            password.clear();
            choiceBox.setValue("Member");
        });

        Pane RegEPane = new Pane();
        RegEPane.getChildren().addAll(RegBackImage, userName, password, choiceBox, Register, backLoginLabel);
        signupPage = new Scene(RegEPane, 900, 563);
        signupPage.getStylesheets().add(getClass().getResource("login.css").toExternalForm());

        // ==================================CoachPage=======================================================

        Image mh = new Image("DB.jpg");
        ImageView mah = new ImageView(mh);
        mah.setFitHeight(1050);
        mah.setFitWidth(1920);

        Pane CoachPane = new Pane();

        TableView<Coach> tableCoach = new TableView<>();
        tableCoach.setEditable(true);

        TableColumn<Coach, String> IdCoach = new TableColumn<>("ID");
        IdCoach.setPrefWidth(60);
        IdCoach.setResizable(false);
        IdCoach.setCellValueFactory(new PropertyValueFactory<Coach, String>("CID"));

        TableColumn<Coach, String> NameCoach = new TableColumn<>("Name");
        NameCoach.setPrefWidth(110);
        NameCoach.setResizable(false);
        NameCoach.setCellValueFactory(new PropertyValueFactory<Coach, String>("coachName"));
        NameCoach.setCellFactory(TextFieldTableCell.forTableColumn());
        NameCoach.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Coach, String>>() {

            @Override
            public void handle(CellEditEvent<Coach, String> arg0) {

                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setCoachName(arg0.getNewValue());
                String id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getGID();
                String name = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getCoachName();

                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE Coaches set coachName ='" + name + "'  WHERE CID='" + id + "'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }

            }

        });

        TableColumn<Coach, String> CoachPhone = new TableColumn<>("Phone Number");
        CoachPhone.setPrefWidth(95);
        CoachPhone.setResizable(false);
        CoachPhone.setCellValueFactory(new PropertyValueFactory<Coach, String>("PhoneNumber"));
        CoachPhone.setCellFactory(TextFieldTableCell.forTableColumn());
        CoachPhone.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Coach, String>>() {

            @Override
            public void handle(CellEditEvent<Coach, String> arg0) {

                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setPhoneNumber(arg0.getNewValue());
                String id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getGID();
                String name = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getPhoneNumber();

                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE Coaches set phoneNumber ='" + name + "'  WHERE CID='" + id + "'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }
            }

        });

        tableCoach.getColumns().addAll(IdCoach, NameCoach, CoachPhone);
        tableCoach.setPrefSize(726, 436);
        tableCoach.setLayoutX(87);
        tableCoach.setLayoutY(110);

        tableCoach.setItems(CoachList);

        Button showBio = new Button("Show Bio", new ImageView("eye.png"));
        showBio.setLayoutX(230);
        showBio.setLayoutY(596);
        showBio.setPrefWidth(70);
        showBio.setPrefHeight(57);
        showBio.setTextFill(Color.BLACK);
        showBio.setContentDisplay(ContentDisplay.TOP);
        showBio.setOnAction(e -> {
            if (tableCoach.getSelectionModel().getSelectedItem() == null) {
                Alert alert = new Alert(Alert.AlertType.NONE, "You Must Select a Row", ButtonType.OK);
                if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                }
                return;
            }
            try {
                Stage stage = new Stage();

                Connection con = db.getConnection().connectDB();

                String sql1 = "SELECT C.coachName, B.WorkingDays, B.WorkingTimes, B.TrainingType FROM Bio B, Coaches C WHERE B.EID = C.CID AND C.CID = '" + tableCoach.getSelectionModel().getSelectedItem().getCID() + "'";
                Statement stmt1 = con.createStatement();
                ResultSet rs = stmt1.executeQuery(sql1);

                // TODO
                String sql2 = "SELECT count(M.CID) FROM Coaches C, Members M WHERE C.CID = M.CID GROUP BY (C.CID) HAVING C.CID = '" + tableCoach.getSelectionModel().getSelectedItem().getCID() + "'"; // coachID in SQL
                Statement stmt2 = con.createStatement();
                ResultSet numberOfStudents = stmt2.executeQuery(sql2);

                int i = 0;

                if (numberOfStudents.next()) {
                    i = Integer.parseInt(numberOfStudents.getString(1));
                }

                rs.next();

                Label coachNameLabel = new Label("Name: " + rs.getString(1));
                Label numberofStudetnsLabel = new Label("Number of students: " + i);
                Label workingDays = new Label("Working Days: " + rs.getString(2));
                Label workingTimesLabel = new Label("Working times: " + rs.getString(3));
                Label trainingTypeLabel = new Label("Training type: " + rs.getString(4));

                VBox labels = new VBox(5);
                labels.getChildren().addAll(coachNameLabel, numberofStudetnsLabel, workingDays, workingTimesLabel, trainingTypeLabel);

                Scene scene = new Scene(labels);
                scene.getStylesheets().add(getClass().getResource("stage2.css").toExternalForm());
                stage.setScene(scene);
                stage.show();

                con.close();

            } catch (Exception e1) {
                e1.printStackTrace();
            }

        });

        Button addCoach = new Button("Add", new ImageView("add.png"));
        addCoach.setLayoutX(567);
        addCoach.setLayoutY(596);
        addCoach.setPrefWidth(70);
        addCoach.setPrefHeight(57);
        addCoach.setTextFill(Color.BLACK);
        addCoach.setContentDisplay(ContentDisplay.TOP);
        addCoach.setOnAction(e -> {

            GridPane gr = new GridPane();

            Stage addCoachStage = new Stage();
            addCoachStage.setResizable(false);
            addCoachStage.setTitle("Add Coach");

            Label gIDLabel = new Label("Coach ID: ");
            TextField gIDInput = new TextField();

            Label nameLabel = new Label("Name: ");
            TextField nameInput = new TextField();

            Label phoneNumLabel = new Label("Phone Number: ");
            TextField phoneNumInput = new TextField();

            Label workingDays = new Label("Working Days: ");
            TextField workingDaysInput = new TextField();

            Label workingTimes = new Label("Working Times: ");
            TextField workingTimesInput = new TextField();

            Label trainingType = new Label("Training Type: ");
            ChoiceBox<String> trainingTypeInput = new ChoiceBox<String>(FXCollections.observableArrayList("Cardio", "Body Building"));

            gr.add(gIDLabel, 0, 0);
            gr.add(gIDInput, 1, 0);
            gr.add(nameLabel, 0, 1);
            gr.add(nameInput, 1, 1);
            gr.add(phoneNumLabel, 0, 2);
            gr.add(phoneNumInput, 1, 2);
            gr.add(workingDays, 0, 3);
            gr.add(workingDaysInput, 1, 3);
            gr.add(workingTimes, 0, 4);
            gr.add(workingTimesInput, 1, 4);
            gr.add(trainingType, 0, 5);
            gr.add(trainingTypeInput, 1, 5);

            Button addCoachButton = new Button("", new ImageView("add.png"));
            addCoachButton.setOnAction(e1 -> {
                if (gIDInput.getText().trim().length() != 0) {
                    try {

                        Connection con = db.getConnection().connectDB();

                        Statement stmt = con.createStatement();

                        String sql1 = "INSERT INTO Employees (EID) VALUES('" + gIDInput.getText() + "')";
                        stmt.execute(sql1);

                        String sql2 = "INSERT INTO Coaches (CID,coachName,phoneNumber) VALUES ('" + gIDInput.getText() + "','" + nameInput.getText() + "','" + phoneNumInput.getText() + "')";
                        stmt.execute(sql2);

                        try {
                            Integer i = (int) (Math.random() * 10000); // Create random ID for Bio

                            String sql3 = "INSERT INTO Bio (BID, EID, WorkingDays, WorkingTimes, TrainingType) VALUES ('" + i.toString() + "', '" + gIDInput.getText() + "', '" + workingDaysInput.getText() + "', '" + workingTimesInput.getText() + "', '" + trainingTypeInput.getValue() + "')";

                            stmt.execute(sql3);

                        } catch (SQLException e2) {
                            displayErrorMassage("Try Again!");
                            e2.getMessage();
                        }

                        con.close();

                        CoachList.add(new Coach(gIDInput.getText(), nameInput.getText(), phoneNumInput.getText()));
                        coachchose.add(gIDInput.getText() + "-" + nameInput.getText() + "-" + workingTimesInput.getText() + "-" + workingDaysInput.getText());

                    } catch (SQLException e2) {
                        Alert alert = new Alert(Alert.AlertType.NONE, "Primary key already exists!", ButtonType.OK);
                        if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                        }
                    } catch (ClassNotFoundException e2) {
                        Alert alert = new Alert(Alert.AlertType.NONE, "Error sending data to database!!!", ButtonType.OK);
                        if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                        }
                    }
                } else {
                    displayErrorMassage("Make sure you enter coach id.");
                }

            });
            gr.add(addCoachButton, 1, 6);
            gr.setMargin(gIDLabel, new Insets(10, 10, 10, 10));
            gr.setMargin(gIDInput, new Insets(10, 10, 10, 10));
            gr.setMargin(nameLabel, new Insets(10, 10, 10, 10));
            gr.setMargin(nameInput, new Insets(10, 10, 10, 10));
            gr.setMargin(phoneNumLabel, new Insets(10, 10, 10, 10));
            gr.setMargin(phoneNumInput, new Insets(10, 10, 10, 10));
            gr.setMargin(addCoachButton, new Insets(10, 10, 10, 10));

            gr.setMargin(workingDays, new Insets(10, 10, 10, 10));
            gr.setMargin(workingDaysInput, new Insets(10, 10, 10, 10));
            gr.setMargin(workingTimes, new Insets(10, 10, 10, 10));
            gr.setMargin(workingTimesInput, new Insets(10, 10, 10, 10));
            gr.setMargin(trainingType, new Insets(10, 10, 10, 10));
            gr.setMargin(trainingTypeInput, new Insets(10, 10, 10, 10));

            gr.setAlignment(Pos.CENTER);
            Scene scene = new Scene(gr, 500, 500);
            scene.getStylesheets().add(getClass().getResource("stage2.css").toExternalForm());

            addCoachStage.setScene(scene);

            addCoachStage.show();

        });

        Button editBio = new Button("Edit Bio", new ImageView("refresh.png"));
        editBio.setLayoutX(403);
        editBio.setLayoutY(596);
        editBio.setPrefWidth(70);
        editBio.setPrefHeight(57);
        editBio.setTextFill(Color.BLACK);
        editBio.setContentDisplay(ContentDisplay.TOP);
        editBio.setOnAction(e -> {
            if (tableCoach.getSelectionModel().getSelectedItem() == null) {
                Alert alert = new Alert(Alert.AlertType.NONE, "You Must Select a Row", ButtonType.OK);
                if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                }
                return;
            }
            try {

                Stage stage = new Stage();
                GridPane gr = new GridPane();
                Connection con = db.getConnection().connectDB();

                String sql1 = "SELECT B.WorkingDays, B.WorkingTimes, B.TrainingType FROM Bio B, Coaches C WHERE B.EID = C.CID AND C.CID = '" + tableCoach.getSelectionModel().getSelectedItem().getCID() + "'";
                Statement stmt1 = con.createStatement();
                ResultSet rs = stmt1.executeQuery(sql1);
                rs.next();

                Label coachNameLabel = new Label("Name: " + tableCoach.getSelectionModel().getSelectedItem().getCoachName());

                Label workingDays = new Label("Working Days: ");
                TextField workingDaysInput = new TextField();
                workingDaysInput.setText(rs.getString(1));

                Label workingTimesLabel = new Label("Working times: ");
                TextField workingTimesInput = new TextField();
                workingTimesInput.setText(rs.getString(2));

                Label trainingType = new Label("Training Type: ");
                ChoiceBox<String> trainingTypeInput = new ChoiceBox<String>(FXCollections.observableArrayList("Cardio", "Body Building"));

                gr.add(coachNameLabel, 0, 0);
                gr.add(workingDays, 0, 1);
                gr.add(workingDaysInput, 1, 1);
                gr.add(workingTimesLabel, 0, 2);
                gr.add(workingTimesInput, 1, 2);
                gr.add(trainingType, 0, 3);
                gr.add(trainingTypeInput, 1, 3);

//                VBox labels = new VBox(14);
//                labels.getChildren().addAll(workingDays, workingTimesLabel, trainingType);
//
//                VBox inputs = new VBox(5);
//                inputs.getChildren().addAll(workingDaysInput, workingTimesInput, trainingTypeInput);
//
//                HBox boxes = new HBox(5);
//                boxes.getChildren().addAll(labels, inputs);

                Button update = new Button("", new ImageView("update.png"));
                update.setOnAction(e1 -> {
                    try {
                        Connection con1 = db.getConnection().connectDB();
                        String sql2 = "UPDATE Bio SET workingDays = '" + workingDaysInput.getText().trim() + "', WorkingTimes = '" + workingTimesInput.getText().trim() + "',trainingType = '" + trainingTypeInput.getValue() + "'" + "WHERE Bio.EID = '" + tableCoach.getSelectionModel().getSelectedItem().getCID() + "'";

                        Statement stmt2 = con1.createStatement();
                        stmt2.executeUpdate(sql2);

                    } catch (ClassNotFoundException | SQLException e2) {
                        e2.printStackTrace();
                    }
                });
                gr.add(update, 1, 4);
                gr.setMargin(coachNameLabel, new Insets(10, 10, 10, 10));
                gr.setMargin(workingDays, new Insets(10, 10, 10, 10));
                gr.setMargin(workingDaysInput, new Insets(10, 10, 10, 10));
                gr.setMargin(workingTimesLabel, new Insets(10, 10, 10, 10));
                gr.setMargin(workingTimesInput, new Insets(10, 10, 10, 10));
                gr.setMargin(trainingType, new Insets(10, 10, 10, 10));
                gr.setMargin(trainingTypeInput, new Insets(10, 10, 10, 10));
                gr.setMargin(update, new Insets(10, 10, 10, 10));

                gr.setAlignment(Pos.CENTER);

                Scene scene = new Scene(gr, 500, 300);
                scene.getStylesheets().add(getClass().getResource("stage2.css").toExternalForm());

                stage.setScene(scene);
                stage.show();

                con.close();

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        Button deleteCoach = new Button("Delete", new ImageView("can.png"));
        deleteCoach.setLayoutX(758);
        deleteCoach.setLayoutY(596);
        deleteCoach.setPrefWidth(70);
        deleteCoach.setPrefHeight(57);

        deleteCoach.setTextFill(Color.BLACK);
        // deleteMember.setFont(javafx.scene.text.Font.font("Uni Sans Thin CAPS", 18));
        deleteCoach.setContentDisplay(ContentDisplay.TOP);
        deleteCoach.setOnAction(e -> {

            if (tableCoach.getSelectionModel().getSelectedItem() == null) {
                Alert alert = new Alert(Alert.AlertType.NONE, "You Must Select a Row", ButtonType.OK);
                if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                }
                return;
            }
            Coach selectedItem = tableCoach.getSelectionModel().getSelectedItem();
            String id = selectedItem.getCID();


            try {
                Connection con = db.getConnection().connectDB();
                String sql = "Update members set CID = null where CID = '" + id + "'";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(sql);
                con.close();

            } catch (Exception e2) {
                e2.printStackTrace();
            }


            tableCoach.getItems().remove(selectedItem);

            try {
                Connection con = db.getConnection().connectDB();
                String sql = "Delete from login WHERE EID='" + id + "'";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(sql);
                con.close();


            } catch (Exception e2) {

            }


            try {

                Connection con = db.getConnection().connectDB();
                String sql = "Delete from Employees WHERE EID='" + id + "'";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(sql);
                con.close();

            } catch (Exception e2) {
                e2.printStackTrace();
            }


            try {
                memberList.clear();
                readMemberData();
                tableMember.refresh();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }

        });
        Button backCoch = new Button("Back", new ImageView("Back.png"));
        backCoch.setLayoutX(55);
        backCoch.setLayoutY(596);
        backCoch.setPrefWidth(70);
        backCoch.setPrefHeight(57);
        backCoch.setTextFill(Color.BLACK);
        backCoch.setContentDisplay(ContentDisplay.TOP);
        backCoch.setOnAction(e -> {
            primaryStage.setScene(MainPage);
            primaryStage.setResizable(false);
        });

        Label CoachLabel = new Label("Coach");
        CoachLabel.setLayoutX(367);
        CoachLabel.setLayoutY(26);
        CoachLabel.setFont(Font.font("Robot", 36));
        CoachLabel.setTextFill(Color.web("#ffffff"));
        CoachLabel.setStyle("-fx-font-size: 30pt; -fx-text-fill: #ffffff;");

        CoachPane.getChildren().addAll(mah, tableCoach, editBio, CoachLabel, addCoach, showBio, deleteCoach, backCoch);

        CoachPage = new Scene(CoachPane, 900, 700);
        CoachPage.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

//==============================NutritionistPage========================================================================
        Image NutrImg = new Image("DB.jpg");
        ImageView NutrView = new ImageView(NutrImg);
        NutrView.setFitHeight(700);
        NutrView.setFitWidth(900);

        TableView<Nutritionist> tableNutritionist = new TableView<>();
        tableNutritionist.setEditable(true);

        TableColumn<Nutritionist, String> IdNutritionist = new TableColumn<>("ID");
        IdNutritionist.setPrefWidth(60);
        IdNutritionist.setResizable(false);
        IdNutritionist.setCellValueFactory(new PropertyValueFactory<Nutritionist, String>("NID"));

        TableColumn<Nutritionist, String> NameNutritionist = new TableColumn<>("Name");
        NameNutritionist.setPrefWidth(95);
        NameNutritionist.setResizable(false);
        NameNutritionist.setCellValueFactory(new PropertyValueFactory<Nutritionist, String>("nutritionistName"));
        NameNutritionist.setCellFactory(TextFieldTableCell.forTableColumn());
        NameNutritionist.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Nutritionist, String>>() {

            @Override
            public void handle(CellEditEvent<Nutritionist, String> arg0) {

                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setNutritionistName(arg0.getNewValue());
                String id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getNID();
                String name = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getNutritionistName();

                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE Nutritionists set nutritionistName ='" + name + "' WHERE NID='" + id + "'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }
            }

        });

        TableColumn<Nutritionist, String> addressNutritionist = new TableColumn<>("Address");
        addressNutritionist.setPrefWidth(120);
        addressNutritionist.setResizable(false);
        addressNutritionist.setCellValueFactory(new PropertyValueFactory<Nutritionist, String>("officeAddress"));
        addressNutritionist.setCellFactory(TextFieldTableCell.forTableColumn());
        addressNutritionist.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Nutritionist, String>>() {

            @Override
            public void handle(CellEditEvent<Nutritionist, String> arg0) {

                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setOfficeAddress(arg0.getNewValue());
                String id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getNID();
                String address = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getOfficeAddress();

                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE Nutritionists set officeAddress ='" + address + "' WHERE NID='" + id + "'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }

            }

        });

        TableColumn<Nutritionist, String> phoneNutritionist = new TableColumn<>("Phone Number");
        phoneNutritionist.setPrefWidth(110);
        phoneNutritionist.setResizable(false);
        phoneNutritionist.setCellValueFactory(new PropertyValueFactory<Nutritionist, String>("phoneNumber"));
        phoneNutritionist.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneNutritionist.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Nutritionist, String>>() {

            @Override
            public void handle(CellEditEvent<Nutritionist, String> arg0) {

                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setPhoneNumber(arg0.getNewValue());
                String id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getNID();
                String phoneNum = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getPhoneNumber();

                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE Nutritionists set phoneNumber ='" + phoneNum + "' WHERE NID='" + id + "'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }

            }

        });

        tableNutritionist.getColumns().addAll(IdNutritionist, NameNutritionist, addressNutritionist, phoneNutritionist);
        tableNutritionist.setPrefSize(726, 436);
        tableNutritionist.setLayoutX(87);
        tableNutritionist.setLayoutY(110);

        tableNutritionist.setItems(nutritionistList);

        Label NutritionistLabel = new Label("Nutritionist");
        NutritionistLabel.setLayoutX(367);
        NutritionistLabel.setLayoutY(26);
        NutritionistLabel.setFont(Font.font("Robot", 36));
        NutritionistLabel.setTextFill(Color.web("#ffffff"));
        NutritionistLabel.setStyle("-fx-font-size: 30pt; -fx-text-fill: #ffffff;");

        Button showBioNu = new Button("Show Bio", new ImageView("eye.png"));
        showBioNu.setLayoutX(230);
        showBioNu.setLayoutY(596);
        showBioNu.setPrefWidth(70);
        showBioNu.setPrefHeight(57);
        showBioNu.setTextFill(Color.BLACK);
        showBioNu.setContentDisplay(ContentDisplay.TOP);
        showBioNu.setOnAction(e -> {
            if (tableNutritionist.getSelectionModel().getSelectedItem() == null) {
                Alert alert = new Alert(Alert.AlertType.NONE, "You Must Select a Row", ButtonType.OK);
                if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                }
                return;
            }
            try {
                Stage stage = new Stage();

                Connection con = db.getConnection().connectDB();

                String sql1 = "SELECT N.nutritionistName, B.WorkingDays, B.WorkingTimes, B.TrainingType FROM Bio B, Nutritionists N WHERE B.EID = N.NID AND N.NID = '" + tableNutritionist.getSelectionModel().getSelectedItem().getNID() + "'";
                Statement stmt1 = con.createStatement();
                ResultSet rs = stmt1.executeQuery(sql1);

                String sql2 = "SELECT count(M.GID) FROM Nutritionists N, Members M WHERE N.NID = M.NID GROUP BY (N.NID) HAVING N.NID='" + tableNutritionist.getSelectionModel().getSelectedItem().getNID() + "'"; // nutritionistsID in
                // SQL
                Statement stmt2 = con.createStatement();
                ResultSet numberOfStudents = stmt2.executeQuery(sql2);

                int i = 0;

                if (numberOfStudents.next()) {
                    i = Integer.parseInt(numberOfStudents.getString(1));
                }

                rs.next();

                Label nutritionistNameLabel = new Label("Name: " + rs.getString(1));
                Label numberofStudetnsLabel = new Label("Number of students: " + i);
                Label workingDays = new Label("Working Days: " + rs.getString(2));
                Label workingTimesLabel = new Label("Working times: " + rs.getString(3));
                Label trainingTypeLabel = new Label("Training type: " + rs.getString(4));

                VBox labels = new VBox(5);
                labels.getChildren().addAll(nutritionistNameLabel, numberofStudetnsLabel, workingDays, workingTimesLabel, trainingTypeLabel);

                Scene scene = new Scene(labels);

                stage.setScene(scene);
                stage.show();

                con.close();

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        Button addNutritionist = new Button("Add", new ImageView("add.png"));
        addNutritionist.setLayoutX(567);
        addNutritionist.setLayoutY(596);
        addNutritionist.setPrefWidth(70);
        addNutritionist.setPrefHeight(57);
        addNutritionist.setTextFill(Color.BLACK);
        // deleteMember.setFont(javafx.scene.text.Font.font("Oranienbaum", 18));
        addNutritionist.setContentDisplay(ContentDisplay.TOP);
        addNutritionist.setOnAction(e -> {

            Stage addNutritionistStage = new Stage();
            addNutritionistStage.setResizable(false);
            addNutritionistStage.setTitle("Add Nutritionist Window");

            GridPane gr = new GridPane();


            Label gIDLabel = new Label("Gym ID: ");
            TextField gIDInput = new TextField();

            Label nameLabel = new Label("Name: ");
            TextField nameInput = new TextField();

            Label addressLabel = new Label("Office Address: ");
            TextField addressInput = new TextField();

            Label phoneNumLabel = new Label("Phone Number: ");
            TextField phoneNumInput = new TextField();

            Label workingDays = new Label("Working Days: ");
            TextField workingDaysInput = new TextField();

            Label workingTimes = new Label("Working Times: ");
            TextField workingTimesInput = new TextField();

            Label trainingType = new Label("Training Type: ");
            ChoiceBox<String> trainingTypeInput = new ChoiceBox<String>(FXCollections.observableArrayList("Cardio", "Body Building"));


            gr.add(gIDLabel, 0, 0);
            gr.add(gIDInput, 1, 0);
            gr.add(nameLabel, 0, 1);
            gr.add(nameInput, 1, 1);
            gr.add(addressLabel, 0, 2);
            gr.add(addressInput, 1, 2);
            gr.add(phoneNumLabel, 0, 3);
            gr.add(phoneNumInput, 1, 3);
            gr.add(workingDays, 0, 4);
            gr.add(workingDaysInput, 1, 4);
            gr.add(workingTimes, 0, 5);
            gr.add(workingTimesInput, 1, 5);
            gr.add(trainingType, 0, 6);
            gr.add(trainingTypeInput, 1, 6);


            Button addNutButton = new Button("", new ImageView("add.png"));
            addNutButton.setOnAction(e1 -> {
                if (gIDInput.getText().trim().length() != 0) {
                    try {

                        Connection con = db.getConnection().connectDB();
                        Statement stmt = con.createStatement();

                        String sql1 = "INSERT INTO Employees (EID) VALUES('" + gIDInput.getText() + "')";
                        stmt.execute(sql1);

                        String sql2 = "INSERT INTO Nutritionists (NID, nutritionistName, phoneNumber, officeAddress) VALUES(" + "'" + gIDInput.getText() + "'" + ", " + "'" + nameInput.getText() + "'" + ", " + "'" + phoneNumInput.getText() + "'" + ", " + "'" + addressInput.getText() + "')";
                        stmt.execute(sql2);

                        Integer i = (int) (Math.random() * 100000); // Create random ID for Bio

                        String sql3 = "INSERT INTO Bio (BID, EID, WorkingDays, WorkingTimes, TrainingType) VALUES ('" + i.toString() + "',  '" + gIDInput.getText() + "', '" + workingDaysInput.getText() + "', '" + workingTimesInput.getText() + "', '" + trainingTypeInput.getValue() + "')";
                        stmt.execute(sql3);

                        con.close();

                        nutritionistList.add(new Nutritionist(gIDInput.getText(), nameInput.getText(), phoneNumInput.getText(), addressInput.getText()));
                        nutrt.add(gIDInput.getText() + "-" + nameInput.getText() + "-" + workingTimesInput.getText() + "-" + workingDaysInput.getText());

                    } catch (SQLException e2) {
                        displayErrorMassage("Primary key already exists!");
                    } catch (ClassNotFoundException e2) {
                        System.out.println("Error sending data to database!!!");
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.NONE, "You Must Fill the Fields", ButtonType.OK);
                    if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                    }
                    return;
                }
            });


            gr.add(addNutButton, 1, 7);

            gr.setMargin(gIDLabel, new Insets(10, 10, 10, 10));
            gr.setMargin(gIDInput, new Insets(10, 10, 10, 10));
            gr.setMargin(nameLabel, new Insets(10, 10, 10, 10));
            gr.setMargin(nameInput, new Insets(10, 10, 10, 10));
            gr.setMargin(addressLabel, new Insets(10, 10, 10, 10));
            gr.setMargin(addressInput, new Insets(10, 10, 10, 10));
            gr.setMargin(phoneNumLabel, new Insets(10, 10, 10, 10));
            gr.setMargin(phoneNumInput, new Insets(10, 10, 10, 10));
            gr.setMargin(workingDays, new Insets(10, 10, 10, 10));
            gr.setMargin(workingDaysInput, new Insets(10, 10, 10, 10));
            gr.setMargin(workingTimes, new Insets(10, 10, 10, 10));
            gr.setMargin(workingTimesInput, new Insets(10, 10, 10, 10));
            gr.setMargin(trainingType, new Insets(10, 10, 10, 10));
            gr.setMargin(trainingTypeInput, new Insets(10, 10, 10, 10));
            gr.setMargin(addNutButton, new Insets(10, 10, 10, 10));


            gr.setAlignment(Pos.CENTER);


            Scene scene = new Scene(gr, 500, 500);
            scene.getStylesheets().add(getClass().getResource("stage2.css").toExternalForm());


            addNutritionistStage.setScene(scene);

            addNutritionistStage.show();
        });

        Button deleteNutritionist = new Button("Delete", new ImageView("can.png"));
        deleteNutritionist.setLayoutX(758);
        deleteNutritionist.setLayoutY(596);
        deleteNutritionist.setPrefWidth(70);
        deleteNutritionist.setPrefHeight(57);
        deleteNutritionist.setTextFill(Color.BLACK);
        deleteNutritionist.setContentDisplay(ContentDisplay.TOP);
        deleteNutritionist.setOnAction(e -> {
            if (tableNutritionist.getSelectionModel().getSelectedItem() == null) {
                Alert alert = new Alert(Alert.AlertType.NONE, "You Must Select a Row", ButtonType.OK);
                if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                }
                return;
            }


            Nutritionist selectedItem = tableNutritionist.getSelectionModel().getSelectedItem();
            String id = selectedItem.getNID();

            tableNutritionist.getItems().remove(selectedItem);


            try {
                Connection con = db.getConnection().connectDB();
                String sql = "Update members set NID = null where NID = '" + id + "'";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(sql);
                con.close();

            } catch (Exception e2) {
                e2.printStackTrace();
            }


            tableCoach.getItems().remove(selectedItem);

            try {
                Connection con = db.getConnection().connectDB();
                String sql = "Delete from login WHERE EID='" + id + "'";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(sql);
                con.close();


            } catch (Exception e2) {

            }


            try {

                Connection con = db.getConnection().connectDB();
                String sql = "Delete from Employees WHERE EID='" + id + "'";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(sql);
                con.close();

            } catch (Exception e2) {
                e2.printStackTrace();
            }


            try {
                memberList.clear();
                readMemberData();
                tableMember.refresh();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }


        });

        Button editBionU = new Button("Edit Bio", new ImageView("refresh.png"));
        editBionU.setLayoutX(403);
        editBionU.setLayoutY(596);
        editBionU.setPrefWidth(70);
        editBionU.setPrefHeight(57);
        editBionU.setTextFill(Color.BLACK);
        editBionU.setContentDisplay(ContentDisplay.TOP);
        editBionU.setOnAction(e -> {
            if (tableNutritionist.getSelectionModel().getSelectedItem() == null) {
                Alert alert = new Alert(Alert.AlertType.NONE, "You Must Select a Row", ButtonType.OK);
                if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                }
                return;
            }
            try {
                Stage stage = new Stage();
                GridPane gr = new GridPane();
                Connection con = db.getConnection().connectDB();

                String sql1 = "SELECT B.WorkingDays, B.WorkingTimes, B.TrainingType FROM Bio B, Nutritionists N WHERE B.EID = N.NID AND N.NID = '" + tableNutritionist.getSelectionModel().getSelectedItem().getNID() + "'";
                Statement stmt1 = con.createStatement();
                ResultSet rs = stmt1.executeQuery(sql1);
                rs.next();

                Label coachNameLabel = new Label("Name: " + tableNutritionist.getSelectionModel().getSelectedItem().getNutritionistName());

                Label workingDays = new Label("Working Days: ");
                TextField workingDaysInput = new TextField();
                workingDaysInput.setText(rs.getString(1));

                Label workingTimesLabel = new Label("Working times: ");
                TextField workingTimesInput = new TextField();
                workingTimesInput.setText(rs.getString(2));

                Label trainingType = new Label("Training Type: ");
                ChoiceBox<String> trainingTypeInput = new ChoiceBox<String>(FXCollections.observableArrayList("Cardio", "Body Building"));

                gr.add(coachNameLabel, 0, 0);
                gr.add(workingDays, 0, 1);
                gr.add(workingDaysInput, 1, 1);
                gr.add(workingTimesLabel, 0, 2);
                gr.add(workingTimesInput, 1, 2);
                gr.add(trainingType, 0, 3);
                gr.add(trainingTypeInput, 1, 3);

                Button update = new Button("", new ImageView("update.png"));
                update.setOnAction(e1 -> {
                    try {
                        Connection con1 = db.getConnection().connectDB();
                        String sql2 = "UPDATE Bio SET workingDays = '" + workingDaysInput.getText().trim() + "', WorkingTimes = '" + workingTimesInput.getText().trim() + "',trainingType = '" + trainingTypeInput.getValue() + "'" + "WHERE Bio.EID = '" + tableNutritionist.getSelectionModel().getSelectedItem().getNID() + "'";

                        Statement stmt2 = con1.createStatement();
                        stmt2.executeUpdate(sql2);

                    } catch (ClassNotFoundException | SQLException e2) {
                        e2.printStackTrace();
                    }
                });
                gr.add(update, 1, 4);

                gr.setMargin(coachNameLabel, new Insets(10, 10, 10, 10));
                gr.setMargin(workingDays, new Insets(10, 10, 10, 10));
                gr.setMargin(workingDaysInput, new Insets(10, 10, 10, 10));
                gr.setMargin(workingTimesLabel, new Insets(10, 10, 10, 10));
                gr.setMargin(workingTimesInput, new Insets(10, 10, 10, 10));
                gr.setMargin(trainingType, new Insets(10, 10, 10, 10));
                gr.setMargin(trainingTypeInput, new Insets(10, 10, 10, 10));
                gr.setMargin(update, new Insets(10, 10, 10, 10));

                gr.setAlignment(Pos.CENTER);

                Scene scene = new Scene(gr, 500, 400);
                scene.getStylesheets().add(getClass().getResource("stage2.css").toExternalForm());


                stage.setScene(scene);
                stage.show();

                con.close();

            } catch (Exception e1) {
                e1.printStackTrace();
            }

        });

        Button backNit = new Button("Back", new ImageView("Back.png"));
        backNit.setLayoutX(55);
        backNit.setLayoutY(596);
        backNit.setPrefWidth(70);
        backNit.setPrefHeight(57);
        backNit.setTextFill(Color.BLACK);
        // deleteNutritionist.setFont(javafx.scene.text.Font.font("Uni Sans Thin CAPS",
        // 18));
        backNit.setContentDisplay(ContentDisplay.TOP);
        backNit.setOnAction(e -> {
            primaryStage.setScene(MainPage);
            primaryStage.setResizable(false);

        });

        Pane nutritionistPane = new Pane();
        nutritionistPane.getChildren().addAll(NutrView, tableNutritionist, NutritionistLabel, editBionU, addNutritionist, deleteNutritionist, backNit, showBioNu);
        nutritionistPage = new Scene(nutritionistPane, 900, 700);
        nutritionistPage.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//================================== WorkOutPlanPage ====================================================================
        Image WorkImg = new Image("DB.jpg");
        ImageView WorkView = new ImageView(WorkImg);
        WorkView.setFitHeight(700);
        WorkView.setFitWidth(900);

        TextField searchWork = new TextField();
        searchWork.setLayoutX(86);
        searchWork.setLayoutY(68);
        searchWork.setPrefWidth(125);
        searchWork.setPrefHeight(30);
        searchWork.setPromptText("Enter a member ID");

        TableView<WorkOutPlan> tableWorkOut = new TableView<>();
        tableWorkOut.setEditable(true);

        TableColumn<WorkOutPlan, String> IdWork = new TableColumn<>("ID");
        IdWork.setPrefWidth(60);
        IdWork.setResizable(false);
        IdWork.setCellValueFactory(new PropertyValueFactory<WorkOutPlan, String>("WID"));

        TableColumn<WorkOutPlan, String> PlantypeWork = new TableColumn<>("Plan Type");
        PlantypeWork.setPrefWidth(95);
        PlantypeWork.setResizable(false);
        PlantypeWork.setCellValueFactory(new PropertyValueFactory<WorkOutPlan, String>("Plantype"));
        ObservableList<String> options = FXCollections.observableArrayList("Cardio", "Bodybuilding");
        PlantypeWork.setCellFactory(ChoiceBoxTableCell.forTableColumn(options));
        PlantypeWork.setOnEditCommit(event -> {
            WorkOutPlan workOutPlan = event.getRowValue();
            workOutPlan.setPlantype(event.getNewValue());
            String name = workOutPlan.getPlantype();
            String id = workOutPlan.getWID();

            try {

                Connection con = db.getConnection().connectDB();
                String sql = "UPDATE WorkOutPlan set Plantype ='" + name + "' WHERE WID='" + id + "'";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(sql);
                con.close();

            } catch (Exception e2) {
                e2.getMessage();
            }

        });

        TableColumn<WorkOutPlan, String> DmWork = new TableColumn<>("DM Percentage ");
        DmWork.setPrefWidth(95);
        DmWork.setResizable(false);
        DmWork.setCellValueFactory(new PropertyValueFactory<WorkOutPlan, String>("DmPercentage"));
        DmWork.setCellFactory(TextFieldTableCell.forTableColumn());
        DmWork.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<WorkOutPlan, String>>() {

            @Override
            public void handle(CellEditEvent<WorkOutPlan, String> arg0) {

                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setDmPercentage(arg0.getNewValue());
                String id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getWID();
                String name = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getDmPercentage();

                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE WorkOutPlan set DmPercentage ='" + name + "' WHERE WID='" + id + "'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }
            }

        });

        TableColumn<WorkOutPlan, String> DfWork = new TableColumn<>("DF Percentage ");
        DfWork.setPrefWidth(95);
        DfWork.setResizable(false);
        DfWork.setCellValueFactory(new PropertyValueFactory<WorkOutPlan, String>("dfPercentage"));
        DfWork.setCellFactory(TextFieldTableCell.forTableColumn());
        DfWork.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<WorkOutPlan, String>>() {

            @Override
            public void handle(CellEditEvent<WorkOutPlan, String> arg0) {

                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setDfPercentage(arg0.getNewValue());
                String id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getWID();
                String name = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getDfPercentage();

                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE WorkOutPlan set dfPercentage ='" + name + "' WHERE WID='" + id + "'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }
            }

        });

        TableColumn<WorkOutPlan, String> deadLineWork = new TableColumn<>("Dead Line ");
        deadLineWork.setPrefWidth(120);
        deadLineWork.setResizable(false);
        deadLineWork.setCellValueFactory(new PropertyValueFactory<WorkOutPlan, String>("Deadline"));
        deadLineWork.setCellFactory(TextFieldTableCell.forTableColumn());
        deadLineWork.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<WorkOutPlan, String>>() {

            @Override
            public void handle(CellEditEvent<WorkOutPlan, String> arg0) {

                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setDeadline(arg0.getNewValue());
                String id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getWID();
                String name = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getDeadline();

                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE WorkOutPlan set Deadline ='" + name + "' WHERE WID='" + id + "'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }
            }

        });

        TableColumn<WorkOutPlan, ImageView> exercisesWork = new TableColumn<>("Exercises");
        exercisesWork.setPrefWidth(280);
        exercisesWork.setResizable(false);
        exercisesWork.setCellValueFactory(new PropertyValueFactory<WorkOutPlan, ImageView>("exercises"));
        exercisesWork.setCellFactory(column -> {
            return new TableCell<WorkOutPlan, ImageView>() {
                private Button button;

                @Override
                protected void updateItem(ImageView item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                    } else {
                        // Create the Button if it doesn't exist yet
                        if (button == null) {
                            button = new Button();
                            button.setOnAction(event -> {

                                WorkOutPlan foodPlan = getTableView().getItems().get(getIndex());

                                try {

                                    FileChooser fileChooser = new FileChooser();
                                    f = fileChooser.showOpenDialog(getScene().getWindow());
                                    path = f.getAbsolutePath();
                                    path = path.replace("\\", "\\\\");
                                    String id = foodPlan.getWID();

                                    Connection con = db.getConnection().connectDB();
                                    String sql = "Update WorkOutPlan set  exercises='" + path + "' where WID='" + id + "'";

                                    Statement stmt = con.createStatement();
                                    stmt.executeUpdate(sql);
//		        					Alert alert = new Alert(Alert.AlertType.NONE, "The Prodect has been Update", ButtonType.OK);
//		        					if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
//		        					}

                                    con.close();

                                } catch (Exception e) {
                                    // TODO: handle exception
                                }

                                if (f != null) {

                                    foodPlan.setExercises(new ImageView(f.toURI().toString()));

                                    getTableView().getItems().set(getIndex(), foodPlan);
                                }
                            });
                        }
                        // Set the Button text and graphic to the current value of the "meals" property
                        button.setText(item.getId());
                        button.setGraphic(item);

                        setGraphic(button);
                    }
                }
            };
        });

        tableWorkOut.getColumns().addAll(IdWork, PlantypeWork, DmWork, DfWork, deadLineWork, exercisesWork);
        tableWorkOut.setPrefSize(726, 436);
        tableWorkOut.setLayoutX(87);
        tableWorkOut.setLayoutY(110);

        FilteredList<WorkOutPlan> FliterWork = new FilteredList<>(WorkOutPlanList, b -> true);
        searchWork.textProperty().addListener((observable, oldValue, newValue) -> {
            FliterWork.setPredicate(m -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (m.getWID().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (m.getPlantype().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (m.getDeadline().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (m.getDfPercentage().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (m.getDmPercentage().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else return false;

            });

        });

        SortedList<WorkOutPlan> sortdata2 = new SortedList<>(FliterWork);
        sortdata2.comparatorProperty().bind(tableWorkOut.comparatorProperty());

        tableWorkOut.setItems(sortdata2);

        Label WorkOutLabel = new Label("WorkOut Plan");
        WorkOutLabel.setLayoutX(367);
        WorkOutLabel.setLayoutY(26);
        WorkOutLabel.setFont(Font.font("Robot", 36));
        WorkOutLabel.setTextFill(Color.web("#ffffff"));
        WorkOutLabel.setStyle("-fx-font-size: 30pt; -fx-text-fill: #ffffff;");

        Button addWorkOut = new Button("Add", new ImageView(new Image("add.png")));
        addWorkOut.setLayoutX(386);
        addWorkOut.setLayoutY(596);
        addWorkOut.setPrefWidth(70);
        addWorkOut.setPrefHeight(57);
        addWorkOut.setContentDisplay(ContentDisplay.TOP);
        addWorkOut.setOnAction(e -> {

            Stage addMemberStage = new Stage();
            addMemberStage.setResizable(false);
            addMemberStage.setTitle("Add WorkOut Plan ");

            GridPane gr = new GridPane();

            Label gIDLabel = new Label("ID: ");
            TextField gIDInput = new TextField();

            Label nameLabel = new Label("Plantype : ");

            ChoiceBox<String> nameInput = new ChoiceBox<String>(FXCollections.observableArrayList("Cardio", "Bodybuilding"));
            nameInput.setValue("Cardio");

            Label ageLabel = new Label("DmPercentage : ");
            TextField ageInput = new TextField();

            Label weightLabel = new Label("dfPercentage : ");
            TextField weightInput = new TextField();

            Label heightLabel = new Label("Deadline : ");

            DatePicker heightInput = new DatePicker();

            Label addressLabel = new Label("exercises : ");
            Button addressInput = new Button("", new ImageView("import.png"));
            addressInput.setPrefHeight(50);
            addressInput.setPrefWidth(50);
            addressInput.setTextFill(Color.BLACK);
            addressInput.setContentDisplay(ContentDisplay.TOP);
            addressInput.setOnAction(em -> {
                FileChooser filechooser = new FileChooser();
                f = filechooser.showOpenDialog(addMemberStage);

                path = f.getAbsolutePath();
                path = path.replace("\\", "\\\\");

            });

            gr.add(gIDLabel, 0, 0);
            gr.add(gIDInput, 1, 0);
            gr.add(nameLabel, 0, 1);
            gr.add(nameInput, 1, 1);
            gr.add(ageLabel, 0, 2);
            gr.add(ageInput, 1, 2);
            gr.add(weightLabel, 0, 3);
            gr.add(weightInput, 1, 3);
            gr.add(heightLabel, 0, 4);
            gr.add(heightInput, 1, 4);
            gr.add(addressLabel, 0, 5);
            gr.add(addressInput, 1, 5);

//            VBox labels = new VBox(10);
//            labels.getChildren().addAll(gIDLabel, nameLabel, ageLabel, weightLabel, heightLabel, addressLabel);
//
//            VBox inputs = new VBox(1);
//            inputs.getChildren().addAll(gIDInput, nameInput, ageInput, weightInput, heightInput, addressInput);
//
//            HBox boxes = new HBox();
//            boxes.getChildren().addAll(labels, inputs);

            Button addMemberButton = new Button("", new ImageView("add.png"));
            addMemberButton.setOnAction(e1 -> {

                try {
                    File f = new File(path);
                    ImageView ii = new ImageView(path);

                    Image img = ii.getImage();

                    Connection con = db.getConnection().connectDB();

                    String sql = "Insert Into WorkOutPlan(WID,Plantype,DmPercentage,dfPercentage,Deadline,exercises) values('" + gIDInput.getText() + "' ," + "'" + nameInput.getValue().toString() + "','" + "%" + ageInput.getText() + "' ,'" + "%" + weightInput.getText() + "','" + heightInput.getValue() + "','" + path + "')";

                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    WorkOutPlanList.add(new WorkOutPlan(gIDInput.getText(), nameInput.getValue(), "%" + ageInput.getText(), "%" + weightInput.getText(), heightInput.getValue().toString(), new ImageView(img)));
                    con.close();
                    path = null;

                } catch (SQLException e2) {

                    Alert alert = new Alert(Alert.AlertType.NONE, "Primary key already exists!", ButtonType.OK);
                    if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                    }
                } catch (ClassNotFoundException e2) {

                    Alert alert = new Alert(Alert.AlertType.NONE, "Error sending data to database!!!", ButtonType.OK);
                    if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                    }
                }

            });

//            VBox everything = new VBox(8);
//            everything.setAlignment(Pos.CENTER);
//            everything.getChildren().addAll(boxes, addMemberButton);

            gr.add(addMemberButton, 1, 6);

            gr.setMargin(gIDLabel, new Insets(10, 10, 10, 10));
            gr.setMargin(gIDInput, new Insets(10, 10, 10, 10));
            gr.setMargin(nameLabel, new Insets(10, 10, 10, 10));
            gr.setMargin(nameInput, new Insets(10, 10, 10, 10));
            gr.setMargin(ageLabel, new Insets(10, 10, 10, 10));
            gr.setMargin(ageInput, new Insets(10, 10, 10, 10));
            gr.setMargin(weightLabel, new Insets(10, 10, 10, 10));
            gr.setMargin(weightInput, new Insets(10, 10, 10, 10));
            gr.setMargin(heightLabel, new Insets(10, 10, 10, 10));
            gr.setMargin(heightInput, new Insets(10, 10, 10, 10));
            gr.setMargin(addressLabel, new Insets(10, 10, 10, 10));
            gr.setMargin(addressInput, new Insets(10, 10, 10, 10));
            gr.setMargin(addMemberButton, new Insets(10, 10, 10, 10));

            gr.setAlignment(Pos.CENTER);
            Scene scene = new Scene(gr, 500, 500);
            scene.getStylesheets().add(getClass().getResource("stage2.css").toExternalForm());

            addMemberStage.setScene(scene);

            addMemberStage.show();
        });

        Button deleteWorkOut = new Button("Delete", new ImageView(new Image("can.png")));
        deleteWorkOut.setLayoutX(721);
        deleteWorkOut.setLayoutY(596);
        deleteWorkOut.setPrefWidth(70);
        deleteWorkOut.setPrefHeight(57);
        deleteWorkOut.setContentDisplay(ContentDisplay.TOP);
        ObservableList<WorkOutPlan> workes = FXCollections.observableArrayList(tableWorkOut.getItems());
        deleteWorkOut.setOnAction(e -> {
            if (tableWorkOut.getSelectionModel().getSelectedItem() == null) {
                Alert alert = new Alert(Alert.AlertType.NONE, "You Must Select a Row", ButtonType.OK);
                if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                }
                return;
            }
            WorkOutPlan selectedItem = tableWorkOut.getSelectionModel().getSelectedItem();
            String id = selectedItem.getWID();
            try {

                Connection con = db.getConnection().connectDB();
                String sql = "UPDATE Members set WID=null Where WID='" + selectedItem.getWID() + "'";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(sql);
                con.close();

            } catch (Exception e2) {
                e2.printStackTrace();
            }



            workes.remove(selectedItem);

            try {

                Connection con = db.getConnection().connectDB();
                String sql = "Delete from WorkOutPlan WHERE WID='" + selectedItem.getWID() + "'";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(sql);
                con.close();

            } catch (Exception e2) {
                e2.getMessage();
            }
            WorkOutPlanList.clear();
            readWorkOutPlanData();
            tableWorkOut.refresh();


            memberList.clear();
            try {
                readMemberData();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            tableMember.refresh();

        });

        Button backWorkOut = new Button("Back", new ImageView(new Image("Back.png")));
        backWorkOut.setLayoutX(113);
        backWorkOut.setLayoutY(596);
        backWorkOut.setPrefWidth(70);
        backWorkOut.setPrefHeight(57);
        backWorkOut.setContentDisplay(ContentDisplay.TOP);

        backWorkOut.setOnAction(e -> {
            primaryStage.setScene(MainPage);
        });

        Pane WorkOutPane = new Pane();
        WorkOutPane.getChildren().addAll(WorkView, tableWorkOut, WorkOutLabel, searchWork, addWorkOut, deleteWorkOut, backWorkOut);
        WorkOutPlanPage = new Scene(WorkOutPane, 900, 700);
        WorkOutPlanPage.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

//========================================== FoodPlanPage =====================================
        Image foodImg = new Image("DB.jpg");
        ImageView foodView = new ImageView(foodImg);
        foodView.setFitHeight(700);
        foodView.setFitWidth(900);

        TextField searchFood = new TextField();
        searchFood.setLayoutX(86);
        searchFood.setLayoutY(68);
        searchFood.setPrefWidth(125);
        searchFood.setPrefHeight(30);
        searchFood.setPromptText("ENTER a member ID");

        TableView<FoodPlan> tableFood = new TableView<>();
        tableFood.setEditable(true);

        TableColumn<FoodPlan, String> IdFood = new TableColumn<>("ID");
        IdFood.setPrefWidth(60);
        IdFood.setResizable(false);
        IdFood.setCellValueFactory(new PropertyValueFactory<FoodPlan, String>("PID"));

        TableColumn<FoodPlan, String> PlantypeFood = new TableColumn<>("Plan Type");
        PlantypeFood.setPrefWidth(95);
        PlantypeFood.setResizable(false);
        PlantypeFood.setCellValueFactory(new PropertyValueFactory<FoodPlan, String>("Plantype"));
        ObservableList<String> options1 = FXCollections.observableArrayList("Cardio", "Bodybuilding");
        PlantypeFood.setCellFactory(ChoiceBoxTableCell.forTableColumn(options1));
        PlantypeFood.setOnEditCommit(event -> {
            FoodPlan workOutPlan = event.getRowValue();
            workOutPlan.setPlantype(event.getNewValue());
            String name = workOutPlan.getPlantype();
            String id = workOutPlan.getPID();

            try {

                Connection con = db.getConnection().connectDB();
                String sql = "UPDATE FoodPlan set Plantype ='" + name + "' WHERE PID='" + id + "'";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(sql);
                con.close();

            } catch (Exception e2) {
                e2.getMessage();
            }

        });

        TableColumn<FoodPlan, String> DmFood = new TableColumn<>("DM Percentage ");
        DmFood.setPrefWidth(95);
        DmFood.setResizable(false);
        DmFood.setCellValueFactory(new PropertyValueFactory<FoodPlan, String>("DmPercentage"));
        DmFood.setCellFactory(TextFieldTableCell.forTableColumn());
        DmFood.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<FoodPlan, String>>() {

            @Override
            public void handle(CellEditEvent<FoodPlan, String> arg0) {

                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setDmPercentage(arg0.getNewValue());
                String id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getPID();
                String name = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getDmPercentage();

                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE FoodPlan set DmPercentage ='" + name + "' WHERE PID='" + id + "'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }
            }

        });

        TableColumn<FoodPlan, String> DfFood = new TableColumn<>("DF Percentage ");
        DfFood.setPrefWidth(95);
        DfFood.setResizable(false);
        DfFood.setCellValueFactory(new PropertyValueFactory<FoodPlan, String>("dfPercentage"));
        DfFood.setCellFactory(TextFieldTableCell.forTableColumn());
        DfFood.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<FoodPlan, String>>() {

            @Override
            public void handle(CellEditEvent<FoodPlan, String> arg0) {

                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setDfPercentage(arg0.getNewValue());
                String id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getPID();
                String name = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getDfPercentage();

                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE FoodPlan set dfPercentage ='" + name + "' WHERE PID='" + id + "'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }
            }

        });

        TableColumn<FoodPlan, String> deadLineFood = new TableColumn<>("Dead Line ");
        deadLineFood.setPrefWidth(95);
        deadLineFood.setResizable(false);
        deadLineFood.setCellValueFactory(new PropertyValueFactory<FoodPlan, String>("Deadline"));
        deadLineFood.setCellFactory(TextFieldTableCell.forTableColumn());
        deadLineFood.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<FoodPlan, String>>() {

            @Override
            public void handle(CellEditEvent<FoodPlan, String> arg0) {

                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setDeadline(arg0.getNewValue());
                String id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getPID();
                String name = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getDeadline();

                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE FoodPlan set Deadline ='" + name + "' WHERE PID='" + id + "'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }
            }

        });

        TableColumn<FoodPlan, ImageView> exercisesFood = new TableColumn<>("Meals");
        exercisesFood.setPrefWidth(300);
        exercisesFood.setResizable(false);
        exercisesFood.setCellValueFactory(new PropertyValueFactory<FoodPlan, ImageView>("meals"));
        exercisesFood.setCellFactory(column -> {
            return new TableCell<FoodPlan, ImageView>() {
                private Button button;

                @Override
                protected void updateItem(ImageView item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                    } else {
                        // Create the Button if it doesn't exist yet
                        if (button == null) {
                            button = new Button();
                            button.setOnAction(event -> {

                                FoodPlan foodPlan = getTableView().getItems().get(getIndex());

                                try {

                                    FileChooser fileChooser = new FileChooser();
                                    f1 = fileChooser.showOpenDialog(getScene().getWindow());
                                    path1 = f1.getAbsolutePath();
                                    path1 = path1.replace("\\", "\\\\");
                                    String id = foodPlan.getPID();

                                    Connection con = db.getConnection().connectDB();
                                    String sql = "Update FoodPlan set  meals='" + path1 + "' where PID='" + id + "'";

                                    Statement stmt = con.createStatement();
                                    stmt.executeUpdate(sql);
//		        					Alert alert = new Alert(Alert.AlertType.NONE, "The Prodect has been Update", ButtonType.OK);
//		        					if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
//		        					}

                                    con.close();

                                } catch (Exception e) {
                                    // TODO: handle exception
                                }

                                if (f1 != null) {

                                    foodPlan.setMeals(new ImageView(f1.toURI().toString()));

                                    getTableView().getItems().set(getIndex(), foodPlan);
                                }
                            });
                        }
                        // Set the Button text and graphic to the current value of the "meals" property
                        button.setText(item.getId());
                        button.setGraphic(item);

                        setGraphic(button);
                    }
                }
            };
        });

        // Set the callback that will be executed when the user commits an edit
        exercisesFood.setOnEditCommit(event -> {
            // Update the table cell with the new value
            event.getTableView().getItems().set(event.getTablePosition().getRow(), event.getRowValue());
        });

        tableFood.getColumns().addAll(IdFood, PlantypeFood, DmFood, DfFood, deadLineFood, exercisesFood);
        tableFood.setPrefSize(726, 436);
        tableFood.setLayoutX(87);
        tableFood.setLayoutY(110);

        FilteredList<FoodPlan> FliterCus = new FilteredList<>(FoodPlanList, b -> true);
        searchFood.textProperty().addListener((observable, oldValue, newValue) -> {
            FliterCus.setPredicate(m -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (m.getPID().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (m.getPlantype().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (m.getDeadline().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (m.getDfPercentage().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (m.getDmPercentage().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else return false;

            });

        });

        SortedList<FoodPlan> sortdata1 = new SortedList<>(FliterCus);
        sortdata1.comparatorProperty().bind(tableFood.comparatorProperty());

        tableFood.setItems(sortdata1);

        Label FoodLabel = new Label("Food Plan");
        FoodLabel.setLayoutX(367);
        FoodLabel.setLayoutY(26);
        FoodLabel.setFont(Font.font("Robot", 36));
        FoodLabel.setTextFill(Color.web("#ffffff"));
        FoodLabel.setStyle("-fx-font-size: 30pt; -fx-text-fill: #ffffff;");

        Button addFood = new Button("Add", new ImageView(new Image("add.png")));
        addFood.setLayoutX(386);
        addFood.setLayoutY(596);
        addFood.setPrefWidth(70);
        addFood.setPrefHeight(57);
        addFood.setContentDisplay(ContentDisplay.TOP);
        addFood.setOnAction(e -> {

            Stage addMemberStage = new Stage();
            addMemberStage.setResizable(false);
            addMemberStage.setTitle("Add WorkOut Plan ");
            GridPane gr = new GridPane();
            Label gIDLabel = new Label("ID: ");
            TextField gIDInput = new TextField();

            Label nameLabel = new Label("Plantype : ");

            ChoiceBox<String> nameInput = new ChoiceBox<String>(FXCollections.observableArrayList("Cardio", "Bodybuilding"));
            nameInput.setValue("Cardio");

            Label ageLabel = new Label("DmPercentage : ");
            TextField ageInput = new TextField();

            Label weightLabel = new Label("dfPercentage : ");
            TextField weightInput = new TextField();

            Label heightLabel = new Label("Deadline : ");

            DatePicker heightInput = new DatePicker();

            Label addressLabel = new Label("meals : ");
            Button addressInput = new Button("", new ImageView("import.png"));
            addressInput.setPrefHeight(50);
            addressInput.setPrefWidth(50);
            addressInput.setTextFill(Color.BLACK);
            addressInput.setContentDisplay(ContentDisplay.TOP);
            addressInput.setOnAction(em -> {
                FileChooser filechooser = new FileChooser();
                f1 = filechooser.showOpenDialog(addMemberStage);

                path1 = f1.getAbsolutePath();
                path1 = path1.replace("\\", "\\\\");

            });

            gr.add(gIDLabel, 0, 0);
            gr.add(gIDInput, 1, 0);
            gr.add(nameLabel, 0, 1);
            gr.add(nameInput, 1, 1);
            gr.add(ageLabel, 0, 2);
            gr.add(ageInput, 1, 2);
            gr.add(weightLabel, 0, 3);
            gr.add(weightInput, 1, 3);
            gr.add(heightLabel, 0, 4);
            gr.add(heightInput, 1, 4);
            gr.add(addressLabel, 0, 5);
            gr.add(addressInput, 1, 5);

//            VBox labels = new VBox(10);
//            labels.getChildren().addAll(gIDLabel, nameLabel, ageLabel, weightLabel, heightLabel, addressLabel);
//
//            VBox inputs = new VBox(1);
//            inputs.getChildren().addAll(gIDInput, nameInput, ageInput, weightInput, heightInput, addressInput);
//
//            HBox boxes = new HBox();
//            boxes.getChildren().addAll(labels, inputs);

            Button addMemberButton = new Button("", new ImageView("add.png"));
            addMemberButton.setOnAction(e1 -> {
                try {

                    try {
                        File f1 = new File(path1);
                        ImageView ii = new ImageView(path1);

                        Image img = ii.getImage();

                        Connection con = db.getConnection().connectDB();

                        String sql = "Insert Into FoodPlan(PID,Plantype,DmPercentage,dfPercentage,Deadline,meals) values('" + gIDInput.getText() + "' ," + "'" + nameInput.getValue().toString() + "','" + "%" + ageInput.getText() + "' ,'" + "%" + weightInput.getText() + "','" + heightInput.getValue() + "','" + path1 + "')";

                        Statement stmt = con.createStatement();
                        stmt.executeUpdate(sql);
                        FoodPlanList.add(new FoodPlan(gIDInput.getText(), nameInput.getValue(), "%" + ageInput.getText(), "%" + weightInput.getText(), heightInput.getValue().toString(), new ImageView(img)));
                        con.close();
                        path1 = null;

                    } catch (SQLException e2) {
                        e2.printStackTrace();

                        Alert alert = new Alert(Alert.AlertType.NONE, "Primary key already exists!", ButtonType.OK);
                        if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                        }
                    } catch (ClassNotFoundException e2) {

                        Alert alert = new Alert(Alert.AlertType.NONE, "Error sending data to database!!!", ButtonType.OK);
                        if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                        }
                    }

                } catch (java.lang.NullPointerException e2) {
                    Alert alert = new Alert(Alert.AlertType.NONE, "You Must choose a Picture", ButtonType.OK);
                    if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                    }
                }
            });

//            VBox everything = new VBox(8);
//            everything.setAlignment(Pos.CENTER);
//            everything.getChildren().addAll(boxes, addMemberButton);

            gr.add(addMemberButton, 1, 6);

            gr.setMargin(gIDLabel, new Insets(10, 10, 10, 10));
            gr.setMargin(gIDInput, new Insets(10, 10, 10, 10));
            gr.setMargin(nameLabel, new Insets(10, 10, 10, 10));
            gr.setMargin(nameInput, new Insets(10, 10, 10, 10));
            gr.setMargin(ageLabel, new Insets(10, 10, 10, 10));
            gr.setMargin(ageInput, new Insets(10, 10, 10, 10));
            gr.setMargin(weightLabel, new Insets(10, 10, 10, 10));
            gr.setMargin(weightInput, new Insets(10, 10, 10, 10));
            gr.setMargin(heightLabel, new Insets(10, 10, 10, 10));
            gr.setMargin(heightInput, new Insets(10, 10, 10, 10));
            gr.setMargin(addressLabel, new Insets(10, 10, 10, 10));
            gr.setMargin(addressInput, new Insets(10, 10, 10, 10));
            gr.setMargin(addMemberButton, new Insets(10, 10, 10, 10));

            gr.setAlignment(Pos.CENTER);
            Scene scene = new Scene(gr, 500, 500);
            scene.getStylesheets().add(getClass().getResource("stage2.css").toExternalForm());

            addMemberStage.setScene(scene);

            addMemberStage.show();
        });

        Button deleteFood = new Button("Delete", new ImageView(new Image("can.png")));
        deleteFood.setLayoutX(721);
        deleteFood.setLayoutY(596);
        deleteFood.setPrefWidth(70);
        deleteFood.setPrefHeight(57);
        deleteFood.setContentDisplay(ContentDisplay.TOP);
        ObservableList<FoodPlan> foods = FXCollections.observableArrayList(tableFood.getItems());
        deleteFood.setOnAction(e -> {
            if (tableFood.getSelectionModel().getSelectedItem() == null) {
                Alert alert = new Alert(Alert.AlertType.NONE, "You Must Select a Row", ButtonType.OK);
                if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                }
                return;
            }

            FoodPlan selectedItem = tableFood.getSelectionModel().getSelectedItem();
            String id = selectedItem.getPID();

            try {

                Connection con = db.getConnection().connectDB();
                String sql = "UPDATE Members set PID=null Where PID='" + selectedItem.getPID() + "'";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(sql);
                con.close();

            } catch (Exception e2) {
                e2.printStackTrace();
            }


            foods.remove(selectedItem);

            try {

                Connection con = db.getConnection().connectDB();
                String sql = "Delete from FoodPlan WHERE PID='" + id + "'";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(sql);
                con.close();

            } catch (Exception e2) {
                e2.getMessage();
            }

            FoodPlanList.clear();
            readFoodPlanData();
            tableFood.refresh();

            memberList.clear();
            try {
                readMemberData();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            tableMember.refresh();
        });

        Button backFood = new Button("Back", new ImageView(new Image("Back.png")));
        backFood.setLayoutX(113);
        backFood.setLayoutY(596);
        backFood.setPrefWidth(70);
        backFood.setPrefHeight(57);
        backFood.setContentDisplay(ContentDisplay.TOP);
        backFood.setOnAction(e -> {
            primaryStage.setScene(MainPage);
            primaryStage.setResizable(false);
        });

        Pane FoddPane = new Pane();

        FoddPane.getChildren().addAll(foodView, tableFood, FoodLabel, searchFood, addFood, deleteFood, backFood);

        FoodPlanPage = new Scene(FoddPane, 900, 700);
        FoodPlanPage.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//===================================Payment==============================================================================

        Image payBack = new Image("DB.jpg");
        ImageView payBackImage = new ImageView(payBack);
        payBackImage.setFitHeight(700);
        payBackImage.setFitWidth(900);
        TextField searchPayment = new TextField();
        searchPayment.setLayoutX(86);
        searchPayment.setLayoutY(68);
        searchPayment.setPrefWidth(125);
        searchPayment.setPrefHeight(30);
        searchPayment.setPromptText("ENTER a member ID");

        TableView<Payment> tablePayment = new TableView<>();
        tablePayment.setEditable(true);

        TableColumn<Payment, String> Gidv = new TableColumn<>("GID");
        Gidv.setPrefWidth(60);
        Gidv.setResizable(false);
        Gidv.setCellValueFactory(new PropertyValueFactory<Payment, String>("GID"));

        TableColumn<Payment, String> Idpy = new TableColumn<>("ID");
        Idpy.setPrefWidth(60);
        Idpy.setResizable(false);
        Idpy.setCellValueFactory(new PropertyValueFactory<Payment, String>("PTID"));

        TableColumn<Payment, Double> amount = new TableColumn<>("Amount");
        amount.setPrefWidth(140);
        amount.setResizable(false);
        amount.setCellValueFactory(new PropertyValueFactory<Payment, Double>("amount"));
        amount.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        amount.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Payment, Double>>() {

            @Override
            public void handle(CellEditEvent<Payment, Double> arg0) {

                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setAmount(arg0.getNewValue());
                int id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getPTID();
                Double amount = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getAmount();

                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE Payment set Amount ='" + amount + "' WHERE PTID='" + id + "'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (SQLException e2) {
                    displayErrorMassage("Make sure to enter a valid amount!");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        });

        TableColumn<Payment, String> datePurchased = new TableColumn<>("Date Purchased");
        datePurchased.setPrefWidth(110);
        datePurchased.setResizable(false);
        datePurchased.setCellValueFactory(new PropertyValueFactory<Payment, String>("date"));

        tablePayment.getColumns().addAll(Idpy, Gidv, amount, datePurchased);
        tablePayment.setPrefSize(726, 436);
        tablePayment.setLayoutX(87);
        tablePayment.setLayoutY(110);


        FilteredList<Payment> FliterPayment = new FilteredList<>(paymentList, b -> true);
        searchPayment.textProperty().addListener((observable, oldValue, newValue) -> {
            FliterPayment.setPredicate(e -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(e.getGID()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else return false;

            });

        });

        SortedList<Payment> sortPayment = new SortedList<>(FliterPayment);
        sortPayment.comparatorProperty().bind(tablePayment.comparatorProperty());

        tablePayment.setItems(sortPayment);

        Label bioLabel1 = new Label("Payments");
        bioLabel1.setLayoutX(367);
        bioLabel1.setLayoutY(26);
        bioLabel1.setFont(Font.font("Robot", 36));
        bioLabel1.setTextFill(Color.web("#ffffff"));
        bioLabel1.setStyle("-fx-font-size: 30pt; -fx-text-fill: #ffffff;");

        Button addPayment = new Button("Add", new ImageView(new Image("add.png")));
        addPayment.setLayoutX(386);
        addPayment.setLayoutY(596);
        addPayment.setPrefWidth(70);
        addPayment.setPrefHeight(57);
        addPayment.setContentDisplay(ContentDisplay.TOP);
        addPayment.setOnAction(e -> {

            Stage addPaymentStage = new Stage();
            addPaymentStage.setResizable(false);
            addPaymentStage.setTitle("Add Payment Window");
            GridPane gr = new GridPane();

            Label PIDLabel = new Label("Payment ID: ");
            TextField PIDInput = new TextField();

            Label amountLabel = new Label("amount: ");
            TextField amountInput = new TextField();

            Label GIDLabel = new Label("Member ID: ");
            TextField GIDInput = new TextField();

            Label purchaseDateLabel = new Label("Purchase Date (DD-MM-YY): ");
            DatePicker purchaseDateInput = new DatePicker();

            gr.add(PIDLabel, 0, 0);
            gr.add(PIDInput, 1, 0);
            gr.add(amountLabel, 0, 1);
            gr.add(amountInput, 1, 1);
            gr.add(purchaseDateLabel, 0, 2);
            gr.add(purchaseDateInput, 1, 2);
            gr.add(GIDLabel, 0, 3);
            gr.add(GIDInput, 1, 3);

//            VBox labels = new VBox(10);
//            labels.getChildren().addAll(PIDLabel, amountLabel, purchaseDateLabel);
//
//            VBox inputs = new VBox(1);
//            inputs.getChildren().addAll(PIDInput, amountInput, purchaseDateInput);
//
//            HBox boxes = new HBox();
//            boxes.getChildren().addAll(labels, inputs);

            Button addPaymentButton = new Button("", new ImageView("add.png"));
            addPaymentButton.setOnAction(e1 -> {

                try {

                    Connection con = db.getConnection().connectDB();

                    String sql = "INSERT INTO payment(amount,dateSale,GID) VALUES ('" + amountInput.getText() + "', '" + purchaseDateInput.getValue().toString() + "', '" + GIDInput.getText() + "')";

                    Statement stmt = con.createStatement();
                    stmt.execute(sql);
                    con.close();

                    paymentList.add(new Payment(Double.parseDouble(amountInput.getText()), purchaseDateInput.getValue().toString(), GIDInput.getText()));

                } catch (SQLException e2) {

                    Alert alert = new Alert(Alert.AlertType.NONE, "Primary key already exists!", ButtonType.OK);
                    if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                    }
                    e2.printStackTrace();
                } catch (ClassNotFoundException e2) {
                    System.out.println("Error sending data to database!!!");
                } catch (InputMismatchException e2) {
                    Alert alert = new Alert(Alert.AlertType.NONE, "Please enter a valid input!", ButtonType.OK);
                    if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                    }
                }

            });
//            VBox everything = new VBox(8);
//            everything.setAlignment(Pos.CENTER);
//            everything.getChildren().addAll(boxes, addPaymentButton);

            gr.add(addPaymentButton, 1, 4);

            gr.setMargin(PIDLabel, new Insets(10, 10, 10, 10));
            gr.setMargin(PIDInput, new Insets(10, 10, 10, 10));
            gr.setMargin(amountLabel, new Insets(10, 10, 10, 10));
            gr.setMargin(amountInput, new Insets(10, 10, 10, 10));
            gr.setMargin(purchaseDateLabel, new Insets(10, 10, 10, 10));
            gr.setMargin(purchaseDateInput, new Insets(10, 10, 10, 10));
            gr.setMargin(GIDLabel, new Insets(10, 10, 10, 10));
            gr.setMargin(GIDInput, new Insets(10, 10, 10, 10));
            gr.setMargin(addPaymentButton, new Insets(10, 10, 10, 10));

            gr.setAlignment(Pos.CENTER);
            Scene scene = new Scene(gr, 500, 300);
            scene.getStylesheets().add(getClass().getResource("stage2.css").toExternalForm());

            addPaymentStage.setScene(scene);

            addPaymentStage.show();
        });

        Button deletePayment = new Button("Delete", new ImageView(new Image("can.png")));
        deletePayment.setLayoutX(721);
        deletePayment.setLayoutY(596);
        deletePayment.setPrefWidth(70);
        deletePayment.setPrefHeight(57);
        deletePayment.setContentDisplay(ContentDisplay.TOP);
        ObservableList<Payment> pay = FXCollections.observableArrayList(tablePayment.getItems());
        deletePayment.setOnAction(e -> {
            if (tablePayment.getSelectionModel().getSelectedItem() == null) {
                Alert alert = new Alert(Alert.AlertType.NONE, "You Must Select a Row", ButtonType.OK);
                if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                }
                return;
            }
            Payment selectedItem = tablePayment.getSelectionModel().getSelectedItem();
            //  int id = selectedItem.getPTID();

            pay.remove(selectedItem);

            try {

                Connection con = db.getConnection().connectDB();
                String sql = "Delete from Payment WHERE PTID='" + selectedItem.getPTID() + "'";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(sql);
                con.close();

            } catch (Exception e2) {
                e2.getMessage();
            }
            paymentList.clear();
            try {
                ReadPayment();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            tablePayment.refresh();

        });

        Button backPayment = new Button("Back", new ImageView(new Image("Back.png")));
        backPayment.setLayoutX(113);
        backPayment.setLayoutY(596);
        backPayment.setPrefWidth(70);
        backPayment.setPrefHeight(57);
        backPayment.setContentDisplay(ContentDisplay.TOP);
        backPayment.setOnAction(e -> {
            primaryStage.setScene(MainPage);
            primaryStage.setResizable(false);
        });

        Pane paymentPane = new Pane();
        paymentPane.getChildren().addAll(payBackImage, tablePayment, searchPayment, bioLabel1, deletePayment, addPayment, backPayment);
        paymentPage = new Scene(paymentPane, 900, 700);
        paymentPage.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

//===================================================Bio page ================
        Image bioBack = new Image("DB.jpg");
        ImageView bioBackImage = new ImageView(bioBack);
        bioBackImage.setFitHeight(700);
        bioBackImage.setFitWidth(900);
        TableView<Bio> tableBio = new TableView<>();
        tableBio.setEditable(true);

        TableColumn<Bio, String> Idbio = new TableColumn<>("BID");
        Idbio.setPrefWidth(60);
        Idbio.setResizable(false);
        Idbio.setCellValueFactory(new PropertyValueFactory<Bio, String>("BID"));

        TableColumn<Bio, String> workingDays = new TableColumn<>("Working Days");
        workingDays.setPrefWidth(140);
        workingDays.setResizable(false);
        workingDays.setCellValueFactory(new PropertyValueFactory<Bio, String>("workingDays"));
        workingDays.setCellFactory(TextFieldTableCell.forTableColumn());
        workingDays.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Bio, String>>() {

            @Override
            public void handle(CellEditEvent<Bio, String> arg0) {

                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setWorkingDays(arg0.getNewValue());
                String id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getBID();
                String workingDays = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getWorkingDays();

                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE Bio set WorkingDays ='" + workingDays + "'  WHERE BID='" + id + "'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }
            }

        });

        TableColumn<Bio, String> workingTimes = new TableColumn<>("Working Times");
        workingTimes.setPrefWidth(110);
        workingTimes.setResizable(false);
        workingTimes.setCellValueFactory(new PropertyValueFactory<Bio, String>("workingTimes"));
        workingTimes.setCellFactory(TextFieldTableCell.forTableColumn());
        workingTimes.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Bio, String>>() {

            @Override
            public void handle(CellEditEvent<Bio, String> arg0) {

                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setWorkingTimes(arg0.getNewValue());
                String id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getBID();
                String workingTimes = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getWorkingTimes();

                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE Bio set WorkingTimes ='" + workingTimes + "'  WHERE BID='" + id + "'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }

            }

        });

        TableColumn<Bio, String> trainingType = new TableColumn<>("Training Type");
        trainingType.setPrefWidth(110);
        trainingType.setResizable(false);
        trainingType.setCellValueFactory(new PropertyValueFactory<Bio, String>("trainingType"));
        ObservableList<String> options4 = FXCollections.observableArrayList("Cardio", "Muscle Growth");
        trainingType.setCellFactory(ChoiceBoxTableCell.forTableColumn(options4));
        trainingType.setOnEditCommit(event -> {
            Bio workOutPlan = event.getRowValue();
            workOutPlan.setTrainingType(event.getNewValue());
            System.out.println("MMMMMMMMMMMMMMMM:" + event.getNewValue());
            String name = workOutPlan.getTrainingType();
            String id = workOutPlan.getBID();

            try {

                Connection con = db.getConnection().connectDB();
                String sql = "UPDATE Bio set TrainingType ='" + name + "' WHERE BID='" + id + "'";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(sql);
                con.close();

            } catch (Exception e2) {
                e2.printStackTrace();
            }

        });

        tableBio.getColumns().addAll(Idbio, workingDays, workingTimes, trainingType);
        tableBio.setPrefSize(726, 436);
        tableBio.setLayoutX(87);
        tableBio.setLayoutY(110);

        tableBio.setItems(bioList);

        Label biolable = new Label("Bio");
        biolable.setLayoutX(367);
        biolable.setLayoutY(26);
        biolable.setFont(Font.font("Robot", 36));
        biolable.setTextFill(Color.web("#ffffff"));
        biolable.setStyle("-fx-font-size: 30pt; -fx-text-fill: #ffffff;");

        Button addBio = new Button("Add", new ImageView(new Image("add.png")));
        addBio.setLayoutX(386);
        addBio.setLayoutY(596);
        addBio.setPrefWidth(70);
        addBio.setPrefHeight(57);
        addBio.setContentDisplay(ContentDisplay.TOP);
        addBio.setOnAction(e -> {

            Stage addBioStage = new Stage();
            addBioStage.setResizable(false);
            addBioStage.setTitle("Add Member Window");

            GridPane gr = new GridPane();

            Label BIDLabel = new Label("Bio ID: ");
            TextField BIDInput = new TextField();

            Label workingDaysLabel = new Label("Working Days: ");
            TextField workingDaysInput = new TextField();

            Label workingTimesLabel = new Label("Working Times: ");
            TextField workingTimesInput = new TextField();

            Label trainingTypeLabel = new Label("Training Type: ");
            ChoiceBox<String> trainingTypeInput = new ChoiceBox<String>(FXCollections.observableArrayList("Cardio", "Muscle Growth"));

            Label numOfStudentsLabel = new Label("Coach Name : ");
            TextField numOfStudentsInput = new TextField();

            gr.add(BIDLabel, 0, 0);
            gr.add(BIDInput, 1, 0);
            gr.add(workingDaysLabel, 0, 1);
            gr.add(workingDaysInput, 1, 1);
            gr.add(workingTimesLabel, 0, 2);
            gr.add(workingTimesInput, 1, 2);
            gr.add(trainingTypeLabel, 0, 3);
            gr.add(trainingTypeInput, 1, 3);
            gr.add(numOfStudentsLabel, 0, 4);
            gr.add(numOfStudentsInput, 1, 4);

//            VBox labels = new VBox(10);
//            labels.getChildren().addAll(BIDLabel, numOfStudentsLabel, workingDaysLabel, workingTimesLabel, trainingTypeLabel);
//
//            VBox inputs = new VBox(1);
//            inputs.getChildren().addAll(BIDInput, numOfStudentsInput, workingDaysInput, workingTimesInput, trainingTypeInput);
//
//            HBox boxes = new HBox();
//            boxes.getChildren().addAll(labels, inputs);

            Button addMemberButton = new Button("", new ImageView("add.png"));
            addMemberButton.setOnAction(e1 -> {

                try {

                    Connection con = db.getConnection().connectDB();

                    String sql = "Insert Into Bio (BID,WorkingDays,WorkingTimes,TrainingType,NumberOfStudents) values(" + "'" + BIDInput.getText() + "'" + ", " + "'" + workingDaysInput.getText() + "'" + ", " + "'" + workingTimesInput.getText() + "'" + ", " + "'" + trainingTypeInput.getValue().toString() + "'" + ", " + numOfStudentsInput.getText() + ")";
                    Statement stmt = con.createStatement();
                    stmt.execute(sql);
                    con.close();

                    bioList.add(new Bio(BIDInput.getText(), numOfStudentsInput.getText(), workingDaysInput.getText(), workingTimesInput.getText(), trainingTypeInput.getValue().toString()));

                } catch (SQLException e2) {
                    displayErrorMassage("Primary key already exists!");
                } catch (ClassNotFoundException e2) {
                    System.out.println("Error sending data to database!!!");
                }

            });

//            VBox everything = new VBox(8);
//            everything.setAlignment(Pos.CENTER);
//            everything.getChildren().addAll(boxes, addMemberButton);
            gr.add(addMemberButton, 1, 5);

            gr.setMargin(BIDLabel, new Insets(10, 10, 10, 10));
            gr.setMargin(BIDInput, new Insets(10, 10, 10, 10));
            gr.setMargin(workingDaysLabel, new Insets(10, 10, 10, 10));
            gr.setMargin(workingDaysInput, new Insets(10, 10, 10, 10));
            gr.setMargin(workingTimesLabel, new Insets(10, 10, 10, 10));
            gr.setMargin(workingTimesInput, new Insets(10, 10, 10, 10));
            gr.setMargin(trainingTypeLabel, new Insets(10, 10, 10, 10));
            gr.setMargin(trainingTypeInput, new Insets(10, 10, 10, 10));
            gr.setMargin(numOfStudentsLabel, new Insets(10, 10, 10, 10));
            gr.setMargin(numOfStudentsInput, new Insets(10, 10, 10, 10));
            gr.setMargin(addMemberButton, new Insets(10, 10, 10, 10));

            gr.setAlignment(Pos.CENTER);
            Scene scene = new Scene(gr, 500, 300);
            scene.getStylesheets().add(getClass().getResource("stage2.css").toExternalForm());

            addBioStage.setScene(scene);

            addBioStage.show();
        });

        Button deleteBio = new Button("Delete", new ImageView(new Image("can.png")));
        deleteBio.setLayoutX(721);
        deleteBio.setLayoutY(596);
        deleteBio.setPrefWidth(70);
        deleteBio.setPrefHeight(57);
        deleteBio.setContentDisplay(ContentDisplay.TOP);
        deleteBio.setOnAction(e -> {
            Bio selectedItem = tableBio.getSelectionModel().getSelectedItem();
            String id = selectedItem.getBID();

            tableBio.getItems().remove(selectedItem);

            try {

                Connection con = db.getConnection().connectDB();
                String sql = "Delete from Bio WHERE BID='" + id + "'";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(sql);
                con.close();

            } catch (Exception e2) {
                e2.getMessage();
            }

        });
        Button backBio = new Button("Back", new ImageView(new Image("Back.png")));
        backBio.setLayoutX(113);
        backBio.setLayoutY(596);
        backBio.setPrefWidth(70);
        backBio.setPrefHeight(57);
        backBio.setContentDisplay(ContentDisplay.TOP);
        backBio.setOnAction(e -> {
            primaryStage.setScene(MainPage);
        });

        Pane bioPane = new Pane();
        bioPane.getChildren().addAll(bioBackImage, tableBio, biolable, addBio, deleteBio, backBio);
        bioPage = new Scene(bioPane, 900, 700);
        bioPage.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//===================== items page ===========================================
        Image itemsback = new Image("DB.jpg");
        ImageView ItemBackImage = new ImageView(itemsback);
        ItemBackImage.setFitHeight(700);
        ItemBackImage.setFitWidth(900);
        TextField searchItems = new TextField();
        searchItems.setLayoutX(86);
        searchItems.setLayoutY(68);
        searchItems.setPrefWidth(125);
        searchItems.setPrefHeight(30);
        searchItems.setPromptText("ENTER a Item ID");

        TableView<Items> ItemsTable = new TableView<>();
        ItemsTable.setEditable(true);
        TableColumn<Items, String> IID = new TableColumn<>("ID");
        IID.setPrefWidth(125);
        IID.setResizable(false);
        IID.setCellValueFactory(new PropertyValueFactory<Items, String>("IID"));

        TableColumn<Items, String> name = new TableColumn<>("Name");
        name.setPrefWidth(125);
        name.setResizable(false);
        name.setCellValueFactory(new PropertyValueFactory<Items, String>("Name"));

        name.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Items, String>>() {

            @Override
            public void handle(CellEditEvent<Items, String> arg0) {

                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setName(arg0.getNewValue());
                String id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getIID();
                String name = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getName();

                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE Items set name ='" + name + "'  WHERE IID='" + id + "'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }
            }

        });

        TableColumn<Items, Double> Price = new TableColumn<>("Price");
        Price.setPrefWidth(100);
        Price.setResizable(false);
        Price.setCellValueFactory(new PropertyValueFactory<Items, Double>("price"));
        Price.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        Price.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Items, Double>>() {

            @Override
            public void handle(CellEditEvent<Items, Double> arg0) {

                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setPrice(arg0.getNewValue());
                String id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getIID();
                double Weight = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getPrice();

                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE Items set price ='" + Weight + "'  WHERE IID='" + id + "'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();
                    ItemsTable.refresh();

                } catch (Exception e2) {
                    e2.getMessage();
                }

            }

        });

        TableColumn<Items, Double> Profit = new TableColumn<>("Profit");
        Profit.setPrefWidth(100);
        Profit.setResizable(false);
        Profit.setCellValueFactory(new PropertyValueFactory<Items, Double>("proft"));

        TableColumn<Items, Double> Purchase = new TableColumn<>("Purchase");
        Purchase.setPrefWidth(100);
        Purchase.setResizable(false);
        Purchase.setCellValueFactory(new PropertyValueFactory<Items, Double>("Purchase"));
        Purchase.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        Purchase.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Items, Double>>() {

            @Override
            public void handle(CellEditEvent<Items, Double> arg0) {

                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setPurchase(arg0.getNewValue());
                String id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getIID();
                double Weight = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getPurchase();

                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "UPDATE Items set purchase ='" + Weight + "'  WHERE IID='" + id + "'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    con.close();

                } catch (Exception e2) {
                    e2.getMessage();
                }

            }

        });

        ItemsTable.getColumns().addAll(IID, name, Price, Profit, Purchase);
        ItemsTable.setPrefSize(726, 436);
        ItemsTable.setLayoutX(87);
        ItemsTable.setLayoutY(110);
        FilteredList<Items> FliterProdect = new FilteredList<>(ItemsData, b -> true);
        searchItems.textProperty().addListener((observable, oldValue, newValue) -> {
            FliterProdect.setPredicate(e -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (e.getIID().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (e.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(e.getPrice()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(e.getPurchase()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else return false;

            });

        });
        SortedList<Items> sortdata = new SortedList<>(FliterProdect);
        sortdata.comparatorProperty().bind(ItemsTable.comparatorProperty());

        ItemsTable.setItems(sortdata);

        Label ItemsLabel1 = new Label("Items");
        ItemsLabel1.setLayoutX(367);
        ItemsLabel1.setLayoutY(26);
        ItemsLabel1.setFont(Font.font("Robot", 36));
        ItemsLabel1.setTextFill(Color.web("#ffffff"));
        ItemsLabel1.setStyle("-fx-font-size: 30pt; -fx-text-fill: #ffffff;");

        Button addItems = new Button("Add", new ImageView(new Image("add.png")));
        addItems.setLayoutX(386);
        addItems.setLayoutY(596);
        addItems.setPrefWidth(70);
        addItems.setPrefHeight(57);
        addItems.setContentDisplay(ContentDisplay.TOP);
        addItems.setOnAction(e -> {
            GridPane gr = new GridPane();

            Stage addPaymentStage = new Stage();
            addPaymentStage.setResizable(false);
            addPaymentStage.setTitle("Add Items Window");

            Label PIDLabel = new Label("Items ID : ");
            TextField PIDInput = new TextField();

            Label amountLabel = new Label("Name : ");
            TextField NameInput = new TextField();

            Label PriceLabel = new Label("Price : ");
            TextField PriceInput = new TextField();

            Label purchaseDateLabel = new Label("Purchase : ");
            TextField PurchaseInput = new TextField();

            gr.add(PIDLabel, 0, 0);
            gr.add(PIDInput, 1, 0);
            gr.add(amountLabel, 0, 1);
            gr.add(NameInput, 1, 1);
            gr.add(PriceLabel, 0, 2);
            gr.add(PriceInput, 1, 2);
            gr.add(purchaseDateLabel, 0, 3);
            gr.add(PurchaseInput, 1, 3);
//            VBox labels = new VBox(10);
//            labels.getChildren().addAll(PIDLabel, amountLabel, PriceLabel, purchaseDateLabel);
//
//            VBox inputs = new VBox(1);
//            inputs.getChildren().addAll(PIDInput, NameInput, PriceInput, PurchaseInput);
//
//            HBox boxes = new HBox();
//            boxes.getChildren().addAll(labels, inputs);

            Button addPaymentButton = new Button("", new ImageView("add.png"));
            addPaymentButton.setOnAction(e1 -> {

                try {

                    Connection con = db.getConnection().connectDB();

                    String sql = "INSERT INTO Items(IID,name,price,purchase) VALUES ('" + PIDInput.getText() + "' ,'" + NameInput.getText() + "', '" + PriceInput.getText() + "' , '" + PurchaseInput.getText() + "')";

                    Statement stmt = con.createStatement();
                    stmt.execute(sql);
                    con.close();

                    ItemsData.add(new Items(PIDInput.getText(), NameInput.getText(), Double.parseDouble(PriceInput.getText()), Double.parseDouble(PurchaseInput.getText())));

                } catch (SQLException e2) {

                    Alert alert = new Alert(Alert.AlertType.NONE, "Primary key already exists!", ButtonType.OK);
                    if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                    }
                    e2.printStackTrace();
                } catch (ClassNotFoundException e2) {
                    System.out.println("Error sending data to database!!!");
                } catch (InputMismatchException e2) {
                    Alert alert = new Alert(Alert.AlertType.NONE, "Please enter a valid input!", ButtonType.OK);
                    if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                    }
                }

            });
//            VBox everything = new VBox(8);
//            everything.setAlignment(Pos.CENTER);
//            everything.getChildren().addAll(boxes, addPaymentButton);

            gr.add(addPaymentButton, 1, 4);

            gr.setMargin(PIDLabel, new Insets(10, 10, 10, 10));
            gr.setMargin(PIDInput, new Insets(10, 10, 10, 10));
            gr.setMargin(amountLabel, new Insets(10, 10, 10, 10));
            gr.setMargin(NameInput, new Insets(10, 10, 10, 10));
            gr.setMargin(PriceLabel, new Insets(10, 10, 10, 10));
            gr.setMargin(PriceInput, new Insets(10, 10, 10, 10));
            gr.setMargin(purchaseDateLabel, new Insets(10, 10, 10, 10));
            gr.setMargin(PurchaseInput, new Insets(10, 10, 10, 10));
            gr.setMargin(addPaymentButton, new Insets(10, 10, 10, 10));

            gr.setAlignment(Pos.CENTER);
            Scene scene = new Scene(gr, 500, 300);
            scene.getStylesheets().add(getClass().getResource("stage2.css").toExternalForm());

            addPaymentStage.setScene(scene);

            addPaymentStage.show();

        });

        ObservableList<Items> ITEMS = FXCollections.observableArrayList(ItemsTable.getItems());
        Button deleteItems = new Button("Delete", new ImageView(new Image("can.png")));
        deleteItems.setLayoutX(721);
        deleteItems.setLayoutY(596);
        deleteItems.setPrefWidth(70);
        deleteItems.setPrefHeight(57);
        deleteItems.setContentDisplay(ContentDisplay.TOP);
        deleteItems.setOnAction(e -> {
            if (ItemsTable.getSelectionModel().getSelectedItem() == null) {
                Alert alert = new Alert(Alert.AlertType.NONE, "You Must Select a Row", ButtonType.OK);
                if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                }
                return;
            }
            Items selectedItem = ItemsTable.getSelectionModel().getSelectedItem();

            ITEMS.remove(selectedItem);

            try {

                Connection con = db.getConnection().connectDB();
                String sql = "Delete from Items WHERE IID='" + selectedItem.getIID() + "'";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(sql);
                con.close();

            } catch (Exception e2) {
                e2.getMessage();
            }
            ItemsData.clear();
            try {
                readItems();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            ItemsTable.refresh();

        });

        Button backItems = new Button("Back", new ImageView(new Image("Back.png")));
        backItems.setLayoutX(113);
        backItems.setLayoutY(596);
        backItems.setPrefWidth(70);
        backItems.setPrefHeight(57);
        backItems.setContentDisplay(ContentDisplay.TOP);
        backItems.setOnAction(e -> {
            primaryStage.setScene(MainPage);
        });

        Pane ItemstPane = new Pane();
        ItemstPane.getChildren().addAll(ItemBackImage, ItemsTable, searchItems, ItemsLabel1, addItems, deleteItems, backItems);
        ItemsPage = new Scene(ItemstPane, 900, 700);
        ItemsPage.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//=========================================================== sALES PAGE ======================
        Image SaleBack = new Image("DB.jpg");
        ImageView SaleBackImage = new ImageView(SaleBack);
        SaleBackImage.setFitHeight(700);
        SaleBackImage.setFitWidth(900);

        TextField ItemsID = new TextField();
        ItemsID.setLayoutX(86);
        ItemsID.setLayoutY(68);
        ItemsID.setPrefWidth(125);
        ItemsID.setPrefHeight(30);
        ItemsID.setPromptText("Enter a Item ID");

        TextField totalamount = new TextField();
        totalamount.setLayoutX(715);
        totalamount.setLayoutY(609);
        totalamount.setPrefWidth(125);
        totalamount.setPrefHeight(30);
        totalamount.setDisable(true);
        totalamount.setPromptText("TOTAL AMOUNT");

        Button Sales = new Button("Sale", new ImageView(new Image("sale.png")));
        Sales.setLayoutX(381);
        Sales.setLayoutY(596);
        Sales.setPrefWidth(70);
        Sales.setPrefHeight(57);
        Sales.setContentDisplay(ContentDisplay.TOP);

        Button backSales = new Button("Back", new ImageView(new Image("Back.png")));
        backSales.setLayoutX(118);
        backSales.setLayoutY(596);
        backSales.setPrefWidth(70);
        backSales.setPrefHeight(57);
        backSales.setContentDisplay(ContentDisplay.TOP);
        backSales.setOnAction(e -> {
            primaryStage.setScene(MainPage);
        });

        TableView<Items> SaleTable = new TableView<>();
        SaleTable.setEditable(true);

        TableColumn<Items, String> ItemName = new TableColumn<>("Name");
        ItemName.setPrefWidth(125);
        ItemName.setResizable(false);
        ItemName.setCellValueFactory(new PropertyValueFactory<Items, String>("Name"));

        TableColumn<Items, Double> ItemPrice = new TableColumn<>("Price");
        ItemPrice.setPrefWidth(125);
        ItemPrice.setResizable(false);
        ItemPrice.setCellValueFactory(new PropertyValueFactory<Items, Double>("price"));

        ItemPrice.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        ItemPrice.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Items, Double>>() {

            @Override
            public void handle(CellEditEvent<Items, Double> arg0) {

                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setPrice(arg0.getNewValue());
                String id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getIID();
                Double amount = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getPrice();

            }

        });

        TableColumn<Items, Integer> ItemQuantity = new TableColumn<>("Quantity");
        ItemQuantity.setPrefWidth(125);
        ItemQuantity.setResizable(false);
        ItemQuantity.setCellValueFactory(new PropertyValueFactory<Items, Integer>("quantity"));
        ItemQuantity.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        ItemQuantity.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Items, Integer>>() {

            @Override
            public void handle(CellEditEvent<Items, Integer> arg0) {

                arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).setQuantity(arg0.getNewValue());
                String id = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getIID();
                int amount = arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()).getQuantity();

            }

        });

        TableColumn<Items, Double> totalItems = new TableColumn<>("Total");
        totalItems.setPrefWidth(125);
        totalItems.setResizable(false);
        totalItems.setCellValueFactory(new PropertyValueFactory<Items, Double>("total"));

        SaleTable.getColumns().addAll(ItemName, ItemPrice, ItemQuantity, totalItems);

        SaleTable.setPrefSize(726, 436);
        SaleTable.setLayoutX(87);
        SaleTable.setLayoutY(110);

        SaleTable.setItems(SalesData);

        Label SaleLabel1 = new Label("Sale Page");
        SaleLabel1.setLayoutX(367);
        SaleLabel1.setLayoutY(26);
        SaleLabel1.setFont(Font.font("Robot", 36));
        SaleLabel1.setTextFill(Color.web("#ffffff"));
        SaleLabel1.setStyle("-fx-font-size: 30pt; -fx-text-fill: #ffffff;");
        ItemsID.setOnAction(e -> {
            try {
                Connection con = db.getConnection().connectDB();
                String sql = "SELECT * FROM items WHERE IID = '" + ItemsID.getText() + "'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                double price = 0;
                double total = 0;
                int quantity = 0;
                String iid = "";
                String names = "";
                double sumAmout = 0;
                double proft = 0;
                double purchase = 0;

                int fg = 0;
                int flag = 0;
                while (rs.next()) {
                    if (fg == 0) {
                        names = rs.getString("name");
                        price = rs.getDouble("price");
                        quantity = rs.getInt("quantity");
                        iid = rs.getString("IID");
                        proft = rs.getDouble("proft");
                        purchase = rs.getDouble("purchase");

                        for (int i = 0; i < SalesData.size(); i++) {
                            if (SalesData.get(i).getIID().equals(ItemsID.getText())) {
                                int n = 0;
                                n = SalesData.get(i).getQuantity();
                                n++;
                                SalesData.get(i).setQuantity(n);

                                SaleTable.refresh();
                                fg = 1;
                                flag = 1;
                                break;

                            }
                        }
                        if (flag == 0) {
                            SalesData.add(new Items(iid, names, price, ++quantity, proft, purchase, total));
                            SaleTable.refresh();
                        }

                        for (Items o : SalesData) {

                            sumAmout = o.getTotal() + sumAmout;
                        }

                        totalamount.setText(NumberFormat.getCurrencyInstance().format(sumAmout));

                    }
                }
                con.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            ItemsID.setText("");

        });

        Sales.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Payment");
            dialog.setHeaderText("Payment");
            dialog.setContentText("Please enter the Members ID:");
            Optional<String> result = dialog.showAndWait();
            TextField fi = dialog.getEditor();
            String idd = fi.getText();
            String names = "";
            String PTID = "";

            try {

                Connection con = db.getConnection().connectDB();
                String sql = "SELECT * FROM members WHERE GID = '" + idd + "'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    names = rs.getString("name");


                } else {

                    Alert alert = new Alert(Alert.AlertType.NONE, "The Member ID Is Not Valid", ButtonType.OK);
                    if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                    }
                    return;

                }

                con.close();


            } catch (Exception r) {
                System.out.println(r);
            }


            Calendar calendar = Calendar.getInstance();
            Date dateae = calendar.getTime();
            String day = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(dateae.getTime());
            LocalDate currentdate = LocalDate.now();
            double sum = 0;

            for (Items o : SalesData) {

                sum = o.getTotal() + sum;
            }

            paymentList.add(new Payment(sum, currentdate.toString(), idd));
            try {
                Connection con = db.getConnection().connectDB();
                String sql = "insert into Payment(amount,dateSale,GID) values ('" + sum + "','" + currentdate.toString() + "' , '" + idd + "' )";
                Statement stmt = con.createStatement();

                stmt.executeUpdate(sql);
                con.close();
            } catch (Exception r) {
                System.out.println(r);
            }
            try {
                paymentList.clear();
                ReadPayment();
                tablePayment.refresh();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }


            try {
                Connection con = db.getConnection().connectDB();
                String sql = "SELECT max(P.PTID) AS MAX  from Payment P where P.GID = '" + idd + "'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    PTID = rs.getString("MAX");
                }
                con.close();
            } catch (Exception r) {
                System.out.println(r);
            }


            try {
                String NameBills = idd + "-" + names + "-" + PTID;

                String path = "C:\\Users\\Lenovo\\Desktop\\ \\ProjectDataBASE12\\bills\\" + NameBills + ".pdf";
                PdfWriter pdfWriter = new PdfWriter(path);
                PdfDocument pdfDocument = new PdfDocument(pdfWriter);
                pdfDocument.setDefaultPageSize(PageSize.A4);
                float x = pdfDocument.getDefaultPageSize().getWidth() / 2;
                float y = pdfDocument.getDefaultPageSize().getHeight() / 2;

                Document document = new Document(pdfDocument);
                float threecol = 190f;
                float twocol = 285f;
                float twocol150 = 435f;
                float twoColumnWidth[] = {twocol150, twocol};
                float threeColumnWidth[] = {threecol, threecol, threecol, threecol};
                float fullWidth[] = {threecol * 3};

                Paragraph onesp = new Paragraph("\n");
                Paragraph twosp = new Paragraph("\n\n");
                Table nestedtable = new Table(new float[]{twocol / 2, twocol / 2});
                Table headerTable = new Table(twoColumnWidth);
                Border nb = new SolidBorder(com.itextpdf.kernel.color.Color.WHITE, 1);

                headerTable.addCell(new Cell().add("Bills").setBold().setFontSize(20f).setBorder(nb).setTextAlignment(TextAlignment.LEFT).setMarginLeft(5));

                nestedtable.addCell(getHeaderTextCell("Day.:"));
                nestedtable.addCell(getHeaderTextCellValue(day));
                nestedtable.addCell(getHeaderTextCell("Bill Date:"));
                nestedtable.addCell(getHeaderTextCellValue("" + currentdate));

                headerTable.addCell(nestedtable.setBorder(nb)).setBorder(nb);
                document.add(headerTable);
                document.add(new Paragraph("\n"));
                Border gb = new SolidBorder(com.itextpdf.kernel.color.Color.GRAY, 2);
                Table tableDivider = new Table(fullWidth);
                document.add(tableDivider.setBorder(gb));
                document.add(onesp);
                Table twoColTable = new Table(twoColumnWidth);
                twoColTable.addCell(getBillingandShippingCell("Billing Information"));
                twoColTable.addCell(getBillingandShippingCell("Shipping Information"));
                document.add(twoColTable.setMarginBottom(12f));

                Table twoColTable2 = new Table(twoColumnWidth);
                twoColTable2.addCell(getCell10fLeft("Company", true));
                twoColTable2.addCell(getCell10fLeft("Name", true));
                twoColTable2.addCell(getCell10fLeft("AbuThaher", false));
                twoColTable2.addCell(getCell10fLeft(names, false)); // NAME
                document.add(twoColTable2);

                Table twoColTable3 = new Table(twoColumnWidth);
                twoColTable3.addCell(getCell10fLeft("Name", true));
                twoColTable3.addCell(getCell10fLeft("Address", true));
                twoColTable3.addCell(getCell10fLeft("" + String.valueOf(names), false));
                twoColTable3.addCell(getCell10fLeft("8570 Gulseth Terra, 3324 Eastwood\nSpringfi, Ma, 01114", false));
                document.add(twoColTable3);
                float oneColoumnwidth[] = {twocol150};
                Table oneColTable1 = new Table(oneColoumnwidth);
                oneColTable1.addCell(getCell10fLeft("Address", true));
                oneColTable1.addCell(getCell10fLeft("8570 Gulseth Terra, 3324 Eastwood\nSpringfi, Ma, 01114", false));
                // oneColTable1.addCell(getCell10fLeft("Email",true));
                // oneColTable1.addCell(getCell10fLeft("stern@example.com",false));
                document.add(oneColTable1.setMarginBottom(10f));

                Table tableDivider2 = new Table(fullWidth);
                Border dgb = new DashedBorder(com.itextpdf.kernel.color.Color.GRAY, 0.5f);
                document.add(tableDivider2.setBorder(dgb));

                Paragraph producPara = new Paragraph("Products");

                document.add(producPara.setBold());
                Table threeColTable1 = new Table(threeColumnWidth);
                threeColTable1.setBackgroundColor(com.itextpdf.kernel.color.Color.BLACK);

                threeColTable1.addCell(new Cell().add("Description").setBold().setFontColor(com.itextpdf.kernel.color.Color.WHITE).setBorder(nb));
                threeColTable1.addCell(new Cell().add("Quantity").setBold().setFontColor(com.itextpdf.kernel.color.Color.WHITE).setTextAlignment(TextAlignment.CENTER).setBorder(nb));
                threeColTable1.addCell(new Cell().add("Price").setBold().setFontColor(com.itextpdf.kernel.color.Color.WHITE).setTextAlignment(TextAlignment.RIGHT).setBorder(nb)).setMarginRight(15f);
                threeColTable1.addCell(new Cell().add("Proft").setBold().setFontColor(com.itextpdf.kernel.color.Color.WHITE).setTextAlignment(TextAlignment.RIGHT).setBorder(nb)).setMarginRight(20f);
                document.add(threeColTable1);

                Table threeColTable2 = new Table(threeColumnWidth);
                String FONT = "C:\\Users\\Lenovo\\Desktop\\ \\ProjectDataBASE12\\src\\main\\resources\\ARIALUNI.TTF";

                PdfFont F = PdfFontFactory.createFont(FONT, PdfEncodings.IDENTITY_H);

                LanguageProcessor al = new ArabicLigaturizer();

                float totalSum = 0;
                float totalProf = 0;
                for (int i = 0; i < SalesData.size(); i++) {

                    double total = SalesData.get(i).getQuantity() * SalesData.get(i).getPrice();
                    System.out.println(SalesData.get(i).getQuantity() + " " + SalesData.get(i).getPurchase());
                    double totalpr = SalesData.get(i).getQuantity() * SalesData.get(i).getProft();
                    totalSum += total;
                    totalProf += totalpr;

                    Paragraph text = new Paragraph(al.process(SalesData.get(i).getName())).setFont(F).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.LEFT);

                    threeColTable2.addCell(new Cell().add(text).setBorder(nb)).setMarginLeft(10f);
                    threeColTable2.addCell(new Cell().add(String.valueOf(SalesData.get(i).getQuantity())).setTextAlignment(TextAlignment.CENTER).setBorder(nb));
                    threeColTable2.addCell(new Cell().add(String.valueOf(total)).setTextAlignment(TextAlignment.RIGHT).setBorder(nb)).setMarginRight(15f);
                    threeColTable2.addCell(new Cell().add(String.valueOf(SalesData.get(i).getProft())).setTextAlignment(TextAlignment.RIGHT).setBorder(nb)).setMarginRight(20f);
                }
                document.add(threeColTable2.setMarginBottom(20f));
                float onetwo[] = {threecol + 125f, threecol * 2};
                Table threeColTable4 = new Table(onetwo);
                threeColTable4.addCell(new Cell().add("").setBorder(nb));
                threeColTable4.addCell(tableDivider2).setBorder(nb);
                document.add(threeColTable4);
                float threeColumnWidth23[] = {threecol, threecol, threecol, threecol, threecol, threecol};
                Table threeColTable3 = new Table(threeColumnWidth23);
                threeColTable3.addCell(new Cell().add("").setBorder(nb)).setMarginLeft(10f);
                threeColTable3.addCell(new Cell().add("Total").setTextAlignment(TextAlignment.CENTER).setBorder(nb));
                threeColTable3.addCell(new Cell().add(String.valueOf(totalSum)).setTextAlignment(TextAlignment.RIGHT).setBorder(nb)).setMarginRight(15f);
                threeColTable3.addCell(new Cell().add("").setBorder(nb)).setMarginLeft(10f);
                threeColTable3.addCell(new Cell().add("Total Proft").setTextAlignment(TextAlignment.CENTER).setBorder(nb));
                threeColTable3.addCell(new Cell().add(String.valueOf(totalProf)).setTextAlignment(TextAlignment.RIGHT).setBorder(nb)).setMarginRight(12f);

                document.add(threeColTable3);
                document.add(tableDivider2);
                document.add(new Paragraph("\n"));
                document.add(tableDivider.setBorder(new SolidBorder(1)).setMarginBottom(15f));
                document.close();

            } catch (Exception e2) {
                e2.getMessage();
            }

            SalesData.clear();
            SaleTable.refresh();

        });

        Pane SalePane = new Pane();
        SalePane.getChildren().addAll(SaleBackImage, SaleTable, ItemsID, SaleLabel1, totalamount, Sales, backSales);
        SalesPage = new Scene(SalePane, 900, 700);
        SalesPage.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

//============================================= MainPage ==============================================

        Image Mainpageimage = new Image("mainPage.jpg");
        ImageView maineBackImage = new ImageView(Mainpageimage);
        maineBackImage.setFitHeight(700);
        maineBackImage.setFitWidth(900);


        Button MemberPAGE = new Button("Member", new ImageView("Members.png"));
        MemberPAGE.setPrefSize(180, 170);
        MemberPAGE.setLayoutX(25);
        MemberPAGE.setLayoutY(197);
        MemberPAGE.setContentDisplay(ContentDisplay.TOP);
        MemberPAGE.setOnAction(e -> {
            primaryStage.setScene(memberPage);
            primaryStage.setResizable(false);
        });

        Button CoachPage1 = new Button("Coach", new ImageView("coach.png"));
        CoachPage1.setPrefSize(180, 170);
        CoachPage1.setLayoutX(250);
        CoachPage1.setLayoutY(197);
        CoachPage1.setContentDisplay(ContentDisplay.TOP);
        CoachPage1.setOnAction(e -> {
            primaryStage.setScene(CoachPage);
            primaryStage.setResizable(false);
        });

        Button NutritionistPAGE = new Button("Nutritionist", new ImageView("nur.png"));
        NutritionistPAGE.setPrefSize(180, 170);
        NutritionistPAGE.setLayoutX(471);
        NutritionistPAGE.setLayoutY(197);
        NutritionistPAGE.setContentDisplay(ContentDisplay.TOP);
        NutritionistPAGE.setOnAction(e -> {
            primaryStage.setScene(nutritionistPage);
            primaryStage.setResizable(false);
        });

        Button WorkOutPlanPage12 = new Button("WorkOutPlan", new ImageView("workPlan.png"));
        WorkOutPlanPage12.setPrefSize(180, 170);
        WorkOutPlanPage12.setLayoutX(696);
        WorkOutPlanPage12.setLayoutY(197);
        WorkOutPlanPage12.setContentDisplay(ContentDisplay.TOP);
        WorkOutPlanPage12.setOnAction(e -> {
            primaryStage.setScene(WorkOutPlanPage);
            primaryStage.setResizable(false);
        });

        Button FoodPlanPAGE = new Button("FoodPlan", new ImageView("foodPlan.png"));
        FoodPlanPAGE.setPrefSize(180, 170);
        FoodPlanPAGE.setLayoutX(25);
        FoodPlanPAGE.setLayoutY(440);
        FoodPlanPAGE.setContentDisplay(ContentDisplay.TOP);
        FoodPlanPAGE.setOnAction(e -> {
            primaryStage.setScene(FoodPlanPage);
            primaryStage.setResizable(false);
        });

        Button PaymentPage = new Button("Payment", new ImageView("paymentPage.png"));
        PaymentPage.setPrefSize(180, 170);
        PaymentPage.setLayoutX(250);
        PaymentPage.setLayoutY(440);
        PaymentPage.setContentDisplay(ContentDisplay.TOP);
        PaymentPage.setOnAction(e -> {
            primaryStage.setScene(paymentPage);
            primaryStage.setResizable(false);
        });

        Button SalePAGEF = new Button("Sale Page", new ImageView("salePage.png"));
        SalePAGEF.setPrefSize(180, 170);
        SalePAGEF.setLayoutX(471);
        SalePAGEF.setLayoutY(440);
        SalePAGEF.setContentDisplay(ContentDisplay.TOP);
        SalePAGEF.setOnAction(e -> {
            primaryStage.setScene(SalesPage);
            primaryStage.setResizable(false);
        });

        Button ItemsPAGE = new Button("Items", new ImageView("itemPage.png"));
        ItemsPAGE.setPrefSize(180, 170);
        ItemsPAGE.setLayoutX(696);
        ItemsPAGE.setLayoutY(440);
        ItemsPAGE.setContentDisplay(ContentDisplay.TOP);
        ItemsPAGE.setOnAction(e -> {
            primaryStage.setScene(ItemsPage);
            primaryStage.setResizable(false);
        });

        Label rankLabel = new Label();
        rankLabel.setLayoutX(740);
        rankLabel.setLayoutY(54);
        rankLabel.setStyle("-fx-text-fill: black; -fx-font-family: 'Barlow Condensed'; -fx-font-size: 15;");

        Label userLabel = new Label();
        userLabel.setLayoutX(740);
        userLabel.setLayoutY(37);
        userLabel.setStyle("-fx-text-fill: black; -fx-font-family: 'Barlow Condensed'; -fx-font-size: 15;");

        Button logoutButton = new Button("LOGOUT", new ImageView("logout.png"));
        logoutButton.setPrefSize(150, 33);
        logoutButton.setLayoutX(740);
        logoutButton.setLayoutY(73);
        logoutButton.setContentDisplay(ContentDisplay.LEFT);
        logoutButton.setOnAction(e -> {

            CoachPage1.setDisable(false);
            NutritionistPAGE.setDisable(false);
            PaymentPage.setDisable(false);
            SalePAGEF.setDisable(false);

            addMember.setDisable(false);
            ShowPay.setDisable(false);
            deleteMember.setDisable(false);
            addFood.setDisable(false);
            deleteFood.setDisable(false);
            addWorkOut.setDisable(false);
            deleteWorkOut.setDisable(false);
            addItems.setDisable(false);
            deleteItems.setDisable(false);


            tableMember.setEditable(true);
            tableFood.setEditable(true);
            ItemsTable.setEditable(true);
            tableWorkOut.setEditable(true);
            primaryStage.setScene(loginPage);
        });

        Pane MainPAGEPane = new Pane();
        MainPAGEPane.getChildren().addAll(maineBackImage, MemberPAGE, CoachPage1, rankLabel, userLabel, logoutButton, NutritionistPAGE, WorkOutPlanPage12, FoodPlanPAGE, PaymentPage, SalePAGEF, ItemsPAGE);
        MainPage = new Scene(MainPAGEPane, 900, 700);
        MainPage.getStylesheets().add(getClass().getResource("mainPage.css").toExternalForm());


        //=============================================loginPage======================================================

        Pane st8 = new Pane();
        Image mh8 = new Image("loginBG.jpg");
        ImageView mah8 = new ImageView(mh8);
        mah8.setFitHeight(563);
        mah8.setFitWidth(900);

        TextField Text1 = new TextField();
        Text1.setPrefHeight(28);
        Text1.setPrefWidth(175);
        Text1.setLayoutX(573);
        Text1.setLayoutY(223);
        Text1.setPromptText("Enter a UserName");

        PasswordField pass = new PasswordField();
        pass.setPrefHeight(28);
        pass.setPrefWidth(175);
        pass.setLayoutX(573);
        pass.setLayoutY(281);
        pass.setPromptText("Enter a Password");

        Hyperlink signupLabel = new Hyperlink("Not Here? Sign Up");
        signupLabel.setLayoutX(600);
        signupLabel.setLayoutY(400);
        signupLabel.setFont(javafx.scene.text.Font.font("Barlow Condensed", 12));
        signupLabel.setTextFill(Color.WHITE);
        signupLabel.setOnAction(e -> {
            Text1.clear();
            pass.clear();
            primaryStage.setScene(signupPage);
        });

        Button But = new Button("Login", new ImageView("key.png"));
        But.setPrefHeight(34);
        But.setPrefWidth(162);
        But.setLayoutX(564);
        But.setLayoutY(357);
        But.setOnAction(e -> {
            String type = "";
            String GID = "";
            String names = "";
            int ages = 0;
            double h = 0;
            double w = 0;
            String add = "";
            String phoneNum = "";
            boolean lk = false;
            String dates = "";
            String cid = "";
            String nid = "";
            String pid = "";
            String wid = "";


            int fg = 0;
            try {
                Connection con = db.getConnection().connectDB();
                String sql = "SELECT * FROM login WHERE username = '" + Text1.getText() + "' AND Password = '" + pass.getText() + "'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    fg = 1;
                    type = rs.getString("AdminType");
                    GID = rs.getString("GID");
                    userLogin = rs.getString("username");
                    rankLogin = rs.getString("AdminType");
                }


            } catch (Exception ex) {
                ex.printStackTrace();
            }

            if (fg == 1 && type.equals("Member")) {


                try {

                    Connection con = db.getConnection().connectDB();
                    String sql = "SELECT m.Name,m.age,m.weight,m.height,m.addrees,m.phoneNumber,m.lockerOp,m.EndDate,m.CID,m.NID,m.PID,m.WID FROM members m WHERE GID = '" + GID + "'";
                    Statement stmt = con.createStatement();

                    ResultSet rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        names = rs.getString("Name");
                        ages = rs.getInt("age");
                        h = rs.getDouble("height");
                        w = rs.getDouble("weight");
                        add = rs.getString("addrees");
                        phoneNum = rs.getString("phoneNumber");
                        lk = rs.getBoolean("lockerOp");
                        dates = rs.getString("EndDate");
                        cid = rs.getString("CID");
                        nid = rs.getString("NID");
                        pid = rs.getString("PID");
                        wid = rs.getString("WID");
                    }

                    con.close();


                } catch (Exception ed) {
                    ed.printStackTrace();
                }


                Member m = new Member(GID, names, ages, h, w, add, phoneNum, lk, dates, cid, nid, pid, wid);
                System.out.println("hererererere :  " + m.getName() + " " + m.getGID() + " " + m.getAge() + " " + m.getHeight() + " " + m.getWeight() + " " + m.getAddress() + " " + m.getCID() + " " + m.getNID() + " " + m.getPID() + " " + m.getWID());
                memberList.clear();
                memberList.add(m);

                tableMember.refresh();

                CoachPage1.setDisable(true);
                NutritionistPAGE.setDisable(true);
                PaymentPage.setDisable(true);
                SalePAGEF.setDisable(true);

                addMember.setDisable(true);
                // ShowPay.setDisable(true);
                deleteMember.setDisable(true);
                addFood.setDisable(true);
                deleteFood.setDisable(true);
                addWorkOut.setDisable(true);
                deleteWorkOut.setDisable(true);
                addItems.setDisable(true);
                deleteItems.setDisable(true);


                //tableMember.setEditable(false);
                tableFood.setEditable(false);
                ItemsTable.setEditable(false);
                tableWorkOut.setEditable(false);
                primaryStage.setScene(MainPage);
            } else if (fg == 1) {
                primaryStage.setScene(MainPage);
            } else if (fg == 0) {
                Alert alert = new Alert(Alert.AlertType.NONE, "Username or Password are not correct or You must SignUp", ButtonType.OK);
                if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                }
            }


            rankLabel.setText("Type : " + rankLogin);
            userLabel.setText("UserName : " + userLogin);
            Text1.clear();
            pass.clear();
        });

        st8.getChildren().addAll(mah8, Text1, pass, But, signupLabel);

        loginPage = new Scene(st8, 900, 563);
        loginPage.getStylesheets().add(getClass().getResource("login.css").toExternalForm());

        // ==========================================================================================
        primaryStage.setScene(loginPage);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    private void displayErrorMassage(String error) {
        Stage errorWindow = new Stage();
        errorWindow.setTitle("Error");

        Label errorMassage = new Label(error);

        errorWindow.setScene(new Scene(errorMassage));
        errorWindow.show();
    }

}
