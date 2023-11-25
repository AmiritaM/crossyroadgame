package com.example.crossyroadteam51;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;


public class MainGameActivity extends AppCompatActivity {

    private ImageView road;
    private ImageView river;
    private ImageView safe;
    private ImageView safe2;
    private ImageView goal;
    private int currentKeyCode;
    private boolean onLog;

    private int score = 0;

    private int lives;
    private int logDirection;
    private ImageView sprite;
    private ImageView car;
    private ImageView bus;
    private ImageView truck;
    private ImageView log2;
    private ImageView log1;
    private int carX = -100;
    private int carY = 1030;
    private int busX = 1500 + 200;
    private int busY = 1000;
    private int truckX = -100;
    private int truckY = 1300;
    private int log2X = -100;
    private int log2Y = 250;
    private int log1X = -100;
    private int log1Y = 500;
    private int spriteX;
    private int spriteY;
    private int counter = 0;
    private boolean won = false;

    private boolean touchingLog1;
    private boolean touchingLog2;
    private boolean updateCalled;
    private boolean updateCalled1;
    private RelativeLayout gameLayout;

    private int screenWidth = 1500;
    private int screenHeight = 1960;

    private List<View> arrCar;
    private List<View> arrBus;
    private List<View> arrTruck;
    private List<View> arrLog1;
    private List<View> arrLog2;

    private Handler handler;
    private int min = 1900;

    private int oldScore = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);
        sprite = findViewById(R.id.sprite);
        gameLayout = findViewById(R.id.gameLayout);
        road = findViewById(R.id.roadTile);
        river = findViewById(R.id.riverTile);
        safe = findViewById(R.id.safe1Tile);
        safe2 = findViewById(R.id.safe2Tile);
        goal = findViewById(R.id.goalTile);

        arrCar = new ArrayList<>();
        arrBus = new ArrayList<>();
        arrTruck = new ArrayList<>();
        arrLog1 = new ArrayList<>();
        arrLog2 = new ArrayList<>();

        RelativeLayout.LayoutParams paramsCar = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        paramsCar.width = 100;
        paramsCar.height = 100;
        RelativeLayout.LayoutParams paramsBus = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        paramsBus.width = 450;
        paramsBus.height = 400;
        RelativeLayout.LayoutParams paramsTruck = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        paramsTruck.width = 300;
        paramsTruck.height = 300;
        RelativeLayout.LayoutParams paramsLog1 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        paramsLog1.width = 500;
        paramsLog1.height = 350;
        RelativeLayout.LayoutParams paramsLog2 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        paramsLog2.width = 200;
        paramsLog2.height = 250;

        for (int x = 0; x < 100; x++) {
            car = new ImageView(this);
            bus = new ImageView(this);
            truck = new ImageView(this);
            log2 = new ImageView(this);
            log1 = new ImageView(this);
            car.setLayoutParams(paramsCar);
            bus.setLayoutParams(paramsBus);
            truck.setLayoutParams(paramsTruck);
            log2.setLayoutParams(paramsLog2);
            log1.setLayoutParams(paramsLog1);
            car.setImageResource(R.drawable.car1);
            bus.setImageResource(R.drawable.bus3);
            truck.setImageResource(R.drawable.truck5);
            log1.setImageResource(R.drawable.log1);
            log2.setImageResource(R.drawable.log2);
            arrCar.add(car);
            arrBus.add(bus);
            arrTruck.add(truck);
            arrLog1.add(log1);
            arrLog2.add(log2);

        }

        String character;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                character = null;
            } else {
                character = extras.getString("character");
                lives = extras.getInt("lives");
            }
        } else {
            character = (String) savedInstanceState.getSerializable("character");
        }
        TextView livesText = findViewById(R.id.livesText);
        livesText.setText("Lives: " + lives);
        if (character.equals("Ladybug")) {
            sprite.setImageResource(R.drawable.ladybug);
        } else if (character.equals("Butterfly")) {
            sprite.setImageResource(R.drawable.butterfly);
        } else if (character.equals("Frog")) {
            sprite.setImageResource(R.drawable.frog);
        }

        handler = new Handler();
        Runnable runnable = getRunnableObject();

        handler.postDelayed(runnable, 5000); // 5 seconds

        //character sprite movement variables
        spriteX = 0;
        spriteY = 1900;
        setPosition(sprite, spriteX, spriteY);

        int spriteWidth = sprite.getWidth();
        int spriteHeight = 100;

        // Set keyboard listener
        gameLayout.setFocusableInTouchMode(true);
        gameLayout.requestFocus();
        gameLayout.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                System.out.println(spriteY);
                // Handle keyboard input
                switch (keyCode) {
                    case KeyEvent.KEYCODE_A:
                        if (spriteX - 10 > 0) {
                            spriteX -= 10;
                            setPosition(sprite, spriteX, spriteY);
                            checkCollisions();
                        }
                        return true;
                    case KeyEvent.KEYCODE_D:
                        if (spriteX + 10 + spriteWidth < screenWidth) {
                            spriteX += 10;
                            setPosition(sprite, spriteX, spriteY);
                            checkCollisions();
                        }
                        return true;
                    case KeyEvent.KEYCODE_W:
                        if (spriteY - 50 > 0) {
                            spriteY -= 50;
                            if (spriteY < min) {
                                min = spriteY;
                                updateScore();
                            }
                            setUpUpdate();
                            setPosition(sprite, spriteX, spriteY);
                            checkCollisions();
                        }
                        return true;
                    case KeyEvent.KEYCODE_S:
                        if (spriteY + 50 + spriteHeight < screenHeight) {
                            spriteY += 50;
                            setPosition(sprite, spriteX, spriteY);
                            checkCollisions();
                        }
                        return true;
                    default:
                        return false;
                }
            }
        });
    }


    // Set position of sprite based on spriteX and spriteY values

    /**
     * Method to update and set the position
     * @param view sprite to update position
     * @param val1 val of x pos
     * @param val2 val of y pos
     */
    private void setPosition(View view, float val1, float val2) {
        view.setX(val1);
        view.setY(val2);
    }

    /**
     * Method to update the score
     */
    public void updateScore() {
        TextView scoreText = findViewById(R.id.scoreText);
        //int currScore = Integer.parseInt(scoreText.getText().toString());
        //score += currScore;
        if (spriteY > 1900) {
            score += 5;
        } else if (spriteY > 1000) {
            score += 10;
        } else if (spriteY > 700) {
            score += 15;
        } else if (spriteY > 200) {
            score += 20;
        } else {
            if (score > oldScore) {
                oldScore = score;
            }
            won = true;
            Intent intent = new Intent(MainGameActivity.this, EndGameActivity.class);
            intent.putExtra("score", oldScore);
            intent.putExtra("won", won);
            startActivity(intent);
        }
        scoreText.setText("Score: " + score);
    }

    public void setUpUpdate() {
        if (updateCalled || updateCalled1) {
            lives += 1;
            TextView livesText = findViewById(R.id.livesText);
            livesText.setText("Lives: " + lives);
            updateCalled = false;
            updateCalled1 = false;
        }
    }
    /**
     * Method to check if a collision took place
     */
    public void checkCollisions() {
        Rect spriteRect = new Rect(spriteX, spriteY, spriteX + sprite.getWidth(),
                spriteY + sprite.getHeight());
        for (View car : arrCar) {
            Rect carRect = new Rect((int) car.getX(), (int) car.getY(),
                    (int) car.getX() + car.getWidth(), (int) car.getY() + car.getHeight());
            if (spriteRect.intersect(carRect)) {
                // Collision detected between sprite and car
                update();
                break;
            }
        }
        for (View bus : arrBus) {
            Rect busRect = new Rect((int) bus.getX(), (int) bus.getY(),
                    (int) bus.getX() + bus.getWidth(), (int) bus.getY() + bus.getHeight());
            if (spriteRect.intersect(busRect)) {
                // Collision detected between sprite and bus
                update();
                break;
            }
        }

        for (View truck : arrTruck) {
            Rect truckRect = new Rect((int) truck.getX(), (int) truck.getY(),
                    (int) truck.getX() + truck.getWidth(), (int) truck.getY() + truck.getHeight());
            if (spriteRect.intersect(truckRect)) {
                // Collision detected between sprite and truck
                update();
                break;
            }
        }

        if (sprite.getY() < 700) {
            touchingLog1 = false;
            touchingLog2 = false;
            updateCalled = false;
            updateCalled1 = false;
            for (View log : arrLog1) {
                Rect logRect = new Rect((int) log.getX(), (int) log.getY(),
                        (int) log.getX() + log.getWidth(), (int) log.getY() + log.getHeight());
                if (spriteRect.intersect(logRect)) {
                    touchingLog1 = true;
                    touchingLog2 = false;
                    final View finalLog = log;
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            spriteX = (int) finalLog.getX() + 100;
                            if (spriteX >= screenWidth && !updateCalled) {
                                updateCalled = true;
                                setPosition(sprite, 0, 1700);
                                update();
                            } else {
                                setPosition(sprite, spriteX, spriteY);
                                sprite.bringToFront();
                                checkCollisions();
                            }
                        }
                    }, 60);
                    break;
                }
            }

            for (View log : arrLog2) {
                Rect logRect = new Rect((int) log.getX(), (int) log.getY(),
                        (int) log.getX() + log.getWidth(), (int) log.getY() + log.getHeight());
                if (spriteRect.intersect(logRect)) {
                    touchingLog2 = true;
                    touchingLog1 = false;
                    final View finalLog = log;
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            spriteX = (int) finalLog.getX() + 100;
                            if (spriteX >= screenWidth && !updateCalled1) {
                                updateCalled1 = true;
                                setPosition(sprite, 0, 1700);
                                update();
                            } else {
                                setPosition(sprite, spriteX - 100, spriteY);
                                sprite.bringToFront();
                                checkCollisions();
                            }
                        }
                    }, 60);
                    break;
                }
            }

            if (!touchingLog1 && !touchingLog2 && sprite.getY() < 700
                    && !updateCalled && !updateCalled1) {
                updateCalled = true;
                update();
            }
        }
    }

    /**
     * Getter to get busX
     * @return x pos of bus
     */
    public int getBusX() {
        return busX;
    }

    public boolean isTouchingLog1() {
        return touchingLog1;
    }

    public boolean isTouchingLog2() {
        return touchingLog2;
    }

    public void setTouchingLog2(boolean touchingLog2) {
        this.touchingLog2 = touchingLog2;
    }

    /**
     * Method to update the lives and score during the game
     */
    void update() {
        lives--;
        TextView livesText = findViewById(R.id.livesText);
        livesText.setText("Lives: " + lives);
        if (score > oldScore) {
            oldScore = score;
        }
        score = 0;
        TextView scoresText = findViewById(R.id.scoreText);
        scoresText.setText("Score: " + score);
        spriteX = 0;
        spriteY = 1900;
        setPosition(sprite, spriteX, spriteY);
        if (lives <= 0) {
            won = false;
            Intent intent = new Intent(MainGameActivity.this, EndGameActivity.class);
            intent.putExtra("score", oldScore);
            intent.putExtra("won", won);
            startActivity(intent);
        }
    }


    /**
     * Getter to return y pos
     * @return return bus y pos
     */
    public int getBusY() {
        return busY;
    }

    /**
     * Getter to return x pos
     * @return x pos of car
     */
    public int getCarX() {
        return carX;
    }
    /**
     * Getter to return y pos
     * @return y pos of car
     */
    public int getCarY() {
        return carY;
    }
    /**
     * Getter to return x pos
     * @return x pos of truck
     */
    public int getTruckX() {
        return truckX;
    }

    public void setTouchingLog1(boolean touchingLog1) {
        this.touchingLog1 = touchingLog1;
    }

    /**
     * Getter to return y pos
     * @return y pos of truck
     */
    public int getTruckY() {
        return truckY;
    }

    public int getLog1X() {
        return log1X;
    }

    public int getLog2X() {
        return log2X;
    }

    public int getLog1Y() {
        return log1Y;
    }

    public int getLog2Y() {
        return log2Y;
    }

    public ImageView getLog1() {
        return log1;
    }

    public ImageView getLog2() {
        return log2;
    }

    /**
     * Getter to return car view
     * @return car ImageView
     */
    public ImageView getCar() {
        return car;
    }
    /**
     * Getter to return truck view
     * @return truck ImageView
     */
    public ImageView getTruck() {
        return truck;
    }
    /**
     * Getter to return bus view
     * @return bus ImageView
     */
    public ImageView getBus() {
        return bus;
    }
    /**
     * Getter to return sprite view
     * @return sprite ImageView
     */
    public ImageView getSprite() {
        return sprite;
    }
    /**
     * Getter to return sprite y
     * @return sprite y pos
     */
    public int getSpriteY() {
        return spriteY;
    }
    /**
     * Getter to return sprite x
     * @return sprite x pos
     */
    public int getSpriteX() {
        return spriteX;
    }
    /**
     * Setter to return sprite x
     * @param x to set
     */
    public void setSpriteX(int x) {
        spriteX = x;
    }
    /**
     * Setter to return sprite y
     * @param y to set
     */
    public void setSpriteY(int y) {
        spriteY = y;
    }
    /**
     * Setter to return score
     * @return int score
     */
    public int getScore() {
        return score;
    }
    /**
     * Setter to return lives
     * @return int lives
     */
    public int getLives() {
        return lives;
    }
    /**
     * Getter to return score
     * @return return score
     */
    public TextView getScoreTextView() {
        return findViewById(R.id.scoreText);
    }

    public void toAdd(ImageView view, int x, int y) {
        gameLayout.addView(view);
        setPosition(view, x, y);
        view.setVisibility(View.VISIBLE);
        view.bringToFront();
    }

    public void finalAdd(ValueAnimator animate, int x) {
        animate.setDuration(x);
        animate.setInterpolator(new LinearInterpolator());
    }

    public void carUpdate(ValueAnimator value, ImageView view) {
        float fraction = (float) value.getAnimatedFraction();
        int transX = (int) (carX + (screenWidth) * fraction);
        view.setTranslationX(transX);
        setPosition(view, transX, carY);
    }

    public void busUpdate(ValueAnimator value, ImageView view) {
        float fraction = (float) value.getAnimatedFraction();
        int transX = (int) (busX - (screenWidth) * fraction);
        view.setTranslationX(transX);
        setPosition(view, transX, busY);
    }
    /**
     * Method to create the annimation
     * @return Annimation
     */
    public Runnable getRunnableObject() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                checkCollisions();
                ImageView car = (ImageView) arrCar.get(counter); // Do something every five seconds
                toAdd(car, carX, carY);
                ImageView bus = (ImageView) arrBus.get(counter);
                toAdd(bus, busX, busY);
                ImageView truck = (ImageView) arrTruck.get(counter);
                toAdd(truck, truckX, truckY);
                ImageView log1 = (ImageView) arrLog1.get(counter);
                toAdd(log1, log1X, log1Y);
                ImageView log2 = (ImageView) arrLog2.get(counter);
                toAdd(log2, log2X, log2Y);
                ValueAnimator animatorCar = ValueAnimator.ofFloat(0, screenWidth);
                finalAdd(animatorCar, 9000);
                animatorCar.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        carUpdate(valueAnimator, car);
                    }
                });
                animatorCar.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                    }
                    @Override
                    public void onAnimationEnd(Animator animator) {
                        ((ViewGroup) car.getParent()).removeView(car);
                    }
                    @Override
                    public void onAnimationCancel(Animator animator) {
                    }
                    @Override
                    public void onAnimationRepeat(Animator animator) {
                    }
                });
                animatorCar.start();
                ValueAnimator animatorBus = ValueAnimator.ofFloat(screenWidth, 0);
                finalAdd(animatorBus, 10000);
                animatorBus.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        busUpdate(valueAnimator, bus);
                    }
                });
                animatorBus.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                    }
                    @Override
                    public void onAnimationEnd(Animator animator) {
                        ((ViewGroup) bus.getParent()).removeView(bus);
                    }
                    @Override
                    public void onAnimationCancel(Animator animator) {
                    }
                    @Override
                    public void onAnimationRepeat(Animator animator) {
                    }
                });
                animatorBus.start();
                ValueAnimator animatorTruck = ValueAnimator.ofFloat(0, screenWidth);
                finalAdd(animatorTruck, 10000);
                animatorTruck.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float fraction = (float) valueAnimator.getAnimatedFraction();
                        int transX = (int) (truckX + (screenWidth) * fraction);
                        truck.setTranslationX(transX);
                        setPosition(truck, transX, truckY);
                    }
                });
                animatorTruck.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                    }
                    @Override
                    public void onAnimationEnd(Animator animator) {
                        ((ViewGroup) truck.getParent()).removeView(truck);
                    }
                    @Override
                    public void onAnimationCancel(Animator animator) {
                    }
                    @Override
                    public void onAnimationRepeat(Animator animator) {
                    }
                });
                animatorTruck.start();
                ValueAnimator animatorLog2 = ValueAnimator.ofFloat(0, screenWidth);
                finalAdd(animatorLog2, 10000);
                animatorLog2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float fraction = (float) valueAnimator.getAnimatedFraction();
                        int transX = (int) (log2X + (screenWidth) * fraction);
                        log2.setTranslationX(transX);
                        setPosition(log2, transX, log2Y);
                    }
                });
                animatorLog2.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                    }
                    @Override
                    public void onAnimationEnd(Animator animator) {
                        ((ViewGroup) log2.getParent()).removeView(log2);
                    }
                    @Override
                    public void onAnimationCancel(Animator animator) {
                    }
                    @Override
                    public void onAnimationRepeat(Animator animator) {
                    }
                });
                animatorLog2.start();
                ValueAnimator animatorLog1 = ValueAnimator.ofFloat(0, screenWidth);
                finalAdd(animatorLog1, 8000);
                animatorLog1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float fraction = (float) valueAnimator.getAnimatedFraction();
                        int transX = (int) (log1X + (screenWidth) * fraction);
                        log1.setTranslationX(transX);
                        setPosition(log1, transX, log1Y);
                    }
                });
                animatorLog1.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                    }
                    @Override
                    public void onAnimationEnd(Animator animator) {
                        ((ViewGroup) log1.getParent()).removeView(log1);
                    }
                    @Override
                    public void onAnimationCancel(Animator animator) {
                    }
                    @Override
                    public void onAnimationRepeat(Animator animator) {
                    }
                });
                animatorLog1.start();
                counter++;
                handler.postDelayed(this, 5500); // 5 seconds
            }
        };
        return runnable;
    }
}