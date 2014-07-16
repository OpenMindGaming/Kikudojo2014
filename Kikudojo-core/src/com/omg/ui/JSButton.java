package com.omg.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.omg.drawing.JSActor;
import com.omg.drawing.JSFont;
import com.omg.sswindler.GameManager;
import com.sun.javafx.scene.control.skin.ButtonSkin;

public class JSButton extends JSActor {

	TextButton button;
	
	public TextButton getGDXButton() {
		return button;
	}
	
	
	Skin skin;
	
	ButtonEvent listener;
	
	public JSButton() {
		init(null);
	}
	
	public JSButton(String text) {
		init(null);
		setText(text);
	}
	
	public JSButton(String text, String texture) {
		init(texture);
		setText(text);
	}
	
	
	public void init(String texture) {
		if(texture == null)
			skin = getBlankSkin();	
		else
			skin = getTexturedSkin(texture);
		button = new TextButton("", skin);
		this.addActor(button);
		
	}
	
	public void setText(String text) {
		button.setText(text);
	}
	
	public static Skin getBlankSkin() {
		return getTexturedSkin("White");
	}
	
	public static Skin getTexturedSkin(String tAlias) {
		Skin skin = new Skin();
		skin.add("white", GameManager.getAssetsManager().getTexture(tAlias));
		skin.add("default", JSFont.loadFont("ktegaki"));

		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("white", Color.WHITE);
		textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
		textButtonStyle.font = skin.getFont("default");
		skin.add("default", textButtonStyle);
		return skin;

		
	}
	
	
	public void setButtonEvent(ButtonEvent e) {
		this.listener = e;
		button.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				// TODO Auto-generated method stub
				Gdx.app.debug("Dialogue", "Button Pressed");
				if (listener != null)
		            listener.onPress();
			}
		});
	}
	
	
}
