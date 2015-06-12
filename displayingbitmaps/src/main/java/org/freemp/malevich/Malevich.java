package org.freemp.malevich;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

/**
 * Created by recoilme on 12/06/15.
 */
public class Malevich extends ImageResizer{

    private final Context context;
    private final boolean debug;

    public static class Builder {
        // required params
        private final Context context;
        // not requried params
        private boolean debug = false;

        public Builder (Context context) {
            if (context == null) {
                throw new IllegalArgumentException("Context must not be null.");
            }
            this.context = context.getApplicationContext();
        }

        public Builder debug (boolean debug) {
            this.debug = debug;
            return this;
        }

        public Malevich build() {
            return new Malevich(this);
        }
    }

    // Malevich init
    private Malevich (Builder builder) {
        super(builder.context, builder.debug);
        context = builder.context;
        debug = builder.debug;
    }

    public boolean isDebug() {
        return debug;
    }
}
