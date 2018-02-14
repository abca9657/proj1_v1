JFLAGS = -g
JC = javac

.SUFFIXES: .java .class

.java.class
$(JC) $(JFLAGS) $*.java

all: compile
@echo -e '[INFO] Done!'
	
CLASSES = \
cs455.overlay.node.MessagingNode.java \
cs455.overlay.node.Node.java \
cs455.overlay.node.RegEntry.java \
cs455.overlay.node.RegisterEntrys.java \
cs455.overlay.node.Registry.java \
cs455.overlay.node.UsedRegIDs.java \

cs455.overlay.transport.TCPConnection.java \
cs455.overlay.transport.TCPReceiver.java \
cs455.overlay.transport.TCPSender.java \
cs455.overlay.transport.TCPServerThread.java \

cs455.overlay.wireformats.Event.java \
cs455.overlay.wireformats.EventFactory.java \
cs455.overlay.wireformats.FindNewID.java \
cs455.overlay.wireformats.OVERLAY_NODE_SENDS_REGISTRATION.java \
cs455.overlay.wireformats.Protocol.java


default: classes

classes: $(CLASSES:.java=.class)

clean:
@echo -e '[INFO] Cleaning Up..'
@-rm -rf cs455/**/**/*.class

compile:
@echo -e '[INFO] Compiling the source..'
@javac -d . cs455/**/**/*.java