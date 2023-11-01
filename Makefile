PO_UILIB_DIR=/usr/share/java


all: core app run


core:
	(cd xxl-core; make $(MFLAGS) all)


app: 
	(cd xxl-app; make $(MFLAGS) all PO_UILIB_DIR=$(PO_UILIB_DIR))


run:
	(java -cp "./xxl-core/xxl-core.jar:./xxl-app/xxl-app.jar:$(PO_UILIB_DIR)/po-uilib.jar" xxl.app.App)


clean:
	(cd xxl-core; make $(MFLAGS) clean)
	(cd xxl-app; make $(MFLAGS) clean)
