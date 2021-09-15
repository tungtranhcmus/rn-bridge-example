import React, {Component} from 'react';
import {
  View,
  Text,
  StyleSheet,
  NativeModules,
  NativeEventEmitter,
  TouchableOpacity,
} from 'react-native';

const {CalendarModule} = NativeModules;
const {DEFAULT_EVENT_NAME} = CalendarModule.getConstants();
const calendarEventEmitter = new NativeEventEmitter(CalendarModule);
class App extends Component {
  constructor(props) {
    super(props);
    this.state = {};
  }

  componentDidMount() {
    calendarEventEmitter.addListener('EventA', eventA => {
      console.log({eventA});
    });

    calendarEventEmitter.addListener('EventB', eventB => {
      console.log({eventB});
    });
  }

  onPress = async () => {
    CalendarModule.createCalendarEvent1('Party', '04-12-2020', eventId => {
      console.log(`Created a new event with id ${eventId}`);
    });

    CalendarModule.createCalendarEventCallback(
      'testName',
      'testLocation',
      error => {
        console.error(`Error found! ${error}`);
      },
      eventId => {
        console.log(`event id ${eventId} returned`);
      },
    );

    try {
      const eventId = await CalendarModule.createCalendarEvent2(
        'Party',
        'my house',
      );
      console.log(`Created a new event with id ${eventId}`);
    } catch (e) {
      console.error(e);
    }
  };

  render() {
    return (
      <View style={styles.container}>
        <TouchableOpacity onPress={this.onPress}>
          <Text style={styles.textSayHi}>Say hi</Text>
        </TouchableOpacity>
      </View>
    );
  }
}

export default App;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  textSayHi: {
    fontSize: 15,
  },
});
