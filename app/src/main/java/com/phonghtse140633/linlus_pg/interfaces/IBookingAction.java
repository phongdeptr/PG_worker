package com.phonghtse140633.linlus_pg.interfaces;

public interface IBookingAction {
    void onDetailed(Object id);
    void onAccepted(Object id);
    void onRejected(Object id);
    void onCompleted(Object id);
}
