
package MainPackage;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * Klasa do tworzenia animacji dla postaci.
 * Zawiera funkcje tworzace animacje.
 */

class Animation {

    /**
     * Animacja pasywna postaci..
     * Porusza ramionami, głową w górę, wraca i zapętla się.
     * @param head przekazuje obiekt głowy postaci.
     * @param arm_b przekazuje obiekt obrazu tylnej ręki postaci.
     * @param arm_f przekazuje obiekt obrazu przedniej ręki postaci.
     */

    static void Idle(ImageView head, ImageView arm_b, ImageView arm_f){

        final Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);

        final KeyValue kvhead = new KeyValue(head.yProperty(), head.getY()-3);
        final KeyValue kvarm_b = new KeyValue(arm_b.yProperty(), arm_b.getY()-3);
        final KeyValue kvarm_f = new KeyValue(arm_f.yProperty(), arm_f.getY()-3);

        final KeyFrame kf1 = new KeyFrame(Duration.seconds(2), kvhead, kvarm_b, kvarm_f);

        timeline.getKeyFrames().add(kf1);

        timeline.play();

    }

    /**
     * Animacja trafionego ataku gracza pierwszego. Zawiera animację bycia uderzonym dla gracza drugiego.
     * @param groupOne przekazuje obiekt grupy części ciała gracza pierwszego.
     * @param groupTwo przekazuje obiekt grupy części ciała gracza pierwszego.
     * @param background przekazuje obiekt obrazu tła, które błyska przy trafieniu.
     * @param event przekazuje funkcję wywołaną przy zakończeniu animacji.
     */

    static void HeadbuttOne(AnchorPane groupOne, AnchorPane groupTwo, Rectangle background, EventHandler<ActionEvent> event){

        final Timeline timeline = new Timeline();
        timeline.setCycleCount(2);
        timeline.setAutoReverse(true);

        timeline.setOnFinished(event);

        final KeyValue kv1 = new KeyValue(groupOne.layoutXProperty(), 700);
        final KeyValue kv2 = new KeyValue(groupOne.rotateProperty(), 60);
        final KeyValue kv3 = new KeyValue(groupTwo.layoutXProperty(), groupTwo.getLayoutX());
        final KeyValue kv4 = new KeyValue(groupTwo.layoutXProperty(), groupTwo.getLayoutX()+20);
        final KeyValue kv5 = new KeyValue(background.opacityProperty(), 1.0);
        final KeyValue kv6 = new KeyValue(background.opacityProperty(), 0);

        final KeyFrame kf1 = new KeyFrame(Duration.millis(120), kv3, kv5);
        final KeyFrame kf2 = new KeyFrame(Duration.millis(200), kv1, kv2, kv4, kv6);

        timeline.getKeyFrames().add(kf1);
        timeline.getKeyFrames().add(kf2);

        timeline.play();

    }

    /**
     * Animacja trafionego ataku gracza drugiego. Zawiera animację bycia uderzonym dla gracza pierwszego.
     * @param groupTwo przekazuje obiekt grupy części ciała gracza pierwszego.
     * @param groupOne przekazuje obiekt grupy części ciała gracza pierwszego.
     * @param background przekazuje obiekt obrazu tła, które błyska przy trafieniu.
     * @param event przekazuje funkcję wywołaną przy zakończeniu animacji.
     */

    static void HeadbuttTwo(AnchorPane groupTwo, AnchorPane groupOne, Rectangle background, EventHandler<ActionEvent> event){

        final Timeline timeline = new Timeline();
        timeline.setCycleCount(2);
        timeline.setAutoReverse(true);

        timeline.setOnFinished(event);

        final KeyValue kv1 = new KeyValue(groupTwo.layoutXProperty(), 200);
        final KeyValue kv2 = new KeyValue(groupTwo.rotateProperty(), -60);
        final KeyValue kv3 = new KeyValue(groupOne.layoutXProperty(), groupOne.getLayoutX());
        final KeyValue kv4 = new KeyValue(groupOne.layoutXProperty(), groupOne.getLayoutX()-20);
        final KeyValue kv5 = new KeyValue(background.opacityProperty(), 1.0);
        final KeyValue kv6 = new KeyValue(background.opacityProperty(), 0);

        final KeyFrame kf1 = new KeyFrame(Duration.millis(120), kv3, kv5);
        final KeyFrame kf2 = new KeyFrame(Duration.millis(200), kv1, kv2, kv4, kv6);

        timeline.getKeyFrames().add(kf1);
        timeline.getKeyFrames().add(kf2);

        timeline.play();

    }

    /**
     * Animacja nietrafionego ataku gracza pierwszego. Zawiera animację unika dla gracza pierwszego.
     * @param groupOne przekazuje obiekt grupy części ciała gracza pierwszego.
     * @param groupTwo przekazuje obiekt grupy części ciała gracza pierwszego.
     * @param event przekazuje funkcję wywołaną przy zakończeniu animacji.
     */

    static void HeadbuttOneMiss(AnchorPane groupOne, AnchorPane groupTwo, EventHandler<ActionEvent> event){

        final Timeline timeline = new Timeline();
        timeline.setCycleCount(2);
        timeline.setAutoReverse(true);

        timeline.setOnFinished(event);

        final KeyValue kv1 = new KeyValue(groupOne.layoutXProperty(), 700);
        final KeyValue kv2 = new KeyValue(groupOne.rotateProperty(), 60);
        final KeyValue kv3 = new KeyValue(groupTwo.layoutXProperty(), groupTwo.getLayoutX()+70);
        final KeyValue kv4 = new KeyValue(groupTwo.rotateProperty(), 60);

        final KeyFrame kf1 = new KeyFrame(Duration.millis(200), kv1, kv2, kv3, kv4);

        timeline.getKeyFrames().add(kf1);

        timeline.play();

    }

    /**
     * Animacja nietrafionego ataku gracza drugiego. Zawiera animację unika dla gracza pierwszego.
     * @param groupTwo przekazuje obiekt grupy części ciała gracza pierwszego.
     * @param groupOne przekazuje obiekt grupy części ciała gracza pierwszego.
     * @param event przekazuje funkcję wywołaną przy zakończeniu animacji.
     */

    static void HeadbuttTwoMiss(AnchorPane groupTwo, AnchorPane groupOne, EventHandler<ActionEvent> event){

        final Timeline timeline = new Timeline();
        timeline.setCycleCount(2);
        timeline.setAutoReverse(true);

        timeline.setOnFinished(event);

        final KeyValue kv1 = new KeyValue(groupTwo.layoutXProperty(), 200);
        final KeyValue kv2 = new KeyValue(groupTwo.rotateProperty(), -60);
        final KeyValue kv3 = new KeyValue(groupOne.layoutXProperty(), groupOne.getLayoutX()-70);
        final KeyValue kv4 = new KeyValue(groupOne.rotateProperty(), -50);

        final KeyFrame kf1 = new KeyFrame(Duration.millis(200), kv1, kv2, kv3, kv4);

        timeline.getKeyFrames().add(kf1);

        timeline.play();

    }

    /**
     * Animacja ruchu komunikatu o zadanych obrazeniach.
     * @param label kieruje nam jaki label ma poruszyc.
     */

    static void DamageAnimate(Label label){

        final Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setOnFinished(event -> {
            label.setOpacity(1.0);
            label.setLayoutY(label.getLayoutY() + 10);
            label.setVisible(false);
        });

        final KeyValue kv1 = new KeyValue(label.opacityProperty(), 0);
        final KeyValue kv2 = new KeyValue(label.layoutYProperty(), label.getLayoutY()-10);
        final KeyFrame kf1 = new KeyFrame(Duration.seconds(1), kv1, kv2);

        timeline.getKeyFrames().add(kf1);

        timeline.play();

    }

}
