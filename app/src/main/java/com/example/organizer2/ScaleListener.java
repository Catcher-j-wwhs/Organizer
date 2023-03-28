package com.example.organizer2;

import android.graphics.Matrix;
import android.view.ScaleGestureDetector;

class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {


    private final Blueprint blueprint;

    public ScaleListener(Blueprint blueprint) {
        this.blueprint = blueprint;
    }

    @Override

    public boolean onScaleBegin(ScaleGestureDetector detector) {

        blueprint.mode = Blueprint.ZOOM;

        return true;

    }


    @Override

    public boolean onScale(ScaleGestureDetector detector) {

        float mScaleFactor = detector.getScaleFactor();

        float origScale = blueprint.saveScale;

        blueprint.saveScale *= mScaleFactor;

        if (blueprint.saveScale > blueprint.maxScale) {

            blueprint.saveScale = blueprint.maxScale;

            mScaleFactor = blueprint.maxScale / origScale;

        } else if (blueprint.saveScale < blueprint.minScale) {

            blueprint.saveScale = blueprint.minScale;

            mScaleFactor = blueprint.minScale / origScale;

        }

        blueprint.right = blueprint.width * blueprint.saveScale - blueprint.width - (2 * blueprint.redundantXSpace * blueprint.saveScale);

        blueprint.bottom = blueprint.height * blueprint.saveScale - blueprint.height - (2 * blueprint.redundantYSpace * blueprint.saveScale);

        if (blueprint.origWidth * blueprint.saveScale <= blueprint.width || blueprint.origHeight * blueprint.saveScale <= blueprint.height) {

            blueprint.matrix.postScale(mScaleFactor, mScaleFactor, blueprint.width / 2, blueprint.height / 2);

            if (mScaleFactor < 1) {

                blueprint.matrix.getValues(blueprint.m);

                float x = blueprint.m[Matrix.MTRANS_X];

                float y = blueprint.m[Matrix.MTRANS_Y];

                if (mScaleFactor < 1) {

                    if (Math.round(blueprint.origWidth * blueprint.saveScale) < blueprint.width) {

                        if (y < -blueprint.bottom)

                            blueprint.matrix.postTranslate(0, -(y + blueprint.bottom));

                        else if (y > 0)

                            blueprint.matrix.postTranslate(0, -y);

                    } else {

                        if (x < -blueprint.right)

                            blueprint.matrix.postTranslate(-(x + blueprint.right), 0);

                        else if (x > 0)

                            blueprint.matrix.postTranslate(-x, 0);

                    }

                }

            }

        } else {

            blueprint.matrix.postScale(mScaleFactor, mScaleFactor, detector.getFocusX(), detector.getFocusY());

            blueprint.matrix.getValues(blueprint.m);

            float x = blueprint.m[Matrix.MTRANS_X];

            float y = blueprint.m[Matrix.MTRANS_Y];

            if (mScaleFactor < 1) {

                if (x < -blueprint.right)

                    blueprint.matrix.postTranslate(-(x + blueprint.right), 0);

                else if (x > 0)

                    blueprint.matrix.postTranslate(-x, 0);

                if (y < -blueprint.bottom)

                    blueprint.matrix.postTranslate(0, -(y + blueprint.bottom));

                else if (y > 0)

                    blueprint.matrix.postTranslate(0, -y);

            }

        }

        return true;

    }

}
