all: xxl-core.jar xxl-app.jar


xxl-core.jar:
	(cd xxl-core; make $(MFLAGS) all)


xxl-app.jar:
	(cd xxl-app; make $(MFLAGS) all)


run:
	(java -cp "./xxl-core/xxl-core.jar:./xxl-app/xxl-app.jar:/usr/share/java/po-uilib.jar" xxl.app.App)


clean:
	(cd xxl-core; make $(MFLAGS) clean)
	(cd xxl-app; make $(MFLAGS) clean)
